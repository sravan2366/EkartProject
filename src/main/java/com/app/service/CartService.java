package com.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.CartDto;
import com.app.dto.ModifyCartDto;
import com.app.dto.PricesDto;
import com.app.dto.UsersDto;
import com.app.entity.Cart;
import com.app.entity.Prices;
import com.app.entity.Users;
import com.app.repository.CartRepository;
import com.app.repository.PricesRepository;
import com.app.repository.ProductsRepository;
import com.app.repository.UsersRepository;
import com.app.response.CartResponseItems;
import com.app.response.CartResponsePrice;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@Autowired
	PricesRepository pricesRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ResponseEntity<CartDto> addToCart(String userName, CartDto cartDto) {
		
		String productName = productsRepository.getProductName(cartDto.getPid());
		
		if (productName == null) {
			cartDto.setResponse("Invalid product details");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cartDto);
		}
		
		System.out.println("111111111111111111: "+ userName);
		Long usersCount = usersRepository.findyByUserName(userName);
		System.out.println("22222222222222222: "+ userName+":"+cartDto.getPid());
		String response = "Successfully added to the cart";
		
		int countQuantity = cartRepository.countQuantityDetails(userName, cartDto.getPid());
		System.out.println("33333333: "+ countQuantity);
		
		if(countQuantity>0) {
			
			countQuantity += cartDto.getQuantity();
			double offerPrice = pricesRepository.findByPid_Fk(cartDto.getPid())*cartDto.getQuantity();
			cartRepository.updateQuantityAndPrice(countQuantity, offerPrice, userName, cartDto.getPid());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartDto);
		}
		else
			countQuantity = cartDto.getQuantity();
		
		cartDto.setQuantity(countQuantity);
		System.out.println("44444444444444444444");
		double offerPrice = pricesRepository.findByPid_Fk(cartDto.getPid())*cartDto.getQuantity();
		System.out.println("555555555555555555");
		cartDto.setOfferPrice(offerPrice);
		
		if(usersCount==0) {
			Random random = new Random();
			UsersDto usersDto = new UsersDto();
			usersDto.setUserName("Guest"+random.nextInt());
			usersRepository.saveAndFlush(modelMapper.map(usersDto, Users.class));
			userName = usersDto.getUserName();
			cartDto.setUserName(userName);
			response += " and created temporary users name for you, please login and change user and password.";
		}
		
		cartDto.setProductName(productName);
		cartDto.setUserName(userName);
		
		cartDto.setResponse(response);
		
		cartRepository.saveAndFlush(modelMapper.map(cartDto, Cart.class));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartDto);
		
	}
	
	public ResponseEntity modifyCart(ModifyCartDto modifyCartDto){
		double counter = cartRepository.countQuantityDetails(modifyCartDto.getUserName(), modifyCartDto.getPid());
		
		if (counter==0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't find cart details");
		}
		
		double offerPrice = pricesRepository.findByPid_Fk(modifyCartDto.getPid());
		offerPrice *= modifyCartDto.getQuantity();
		
		cartRepository.updateQuantityAndPrice(modifyCartDto.getQuantity(), offerPrice, modifyCartDto.getUserName(), modifyCartDto.getPid());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(modifyCartDto);
		
			
	}
	
	public ResponseEntity<CartResponsePrice> viewCartByUser(String userName){
		List<Cart> cartList = cartRepository.findByUserName(userName);
		if (cartList.size() > 0) {
			List<CartResponseItems> cartItems= new ArrayList<>();
			double totalPrice=0;
			double totalDeliveryCharge=0;
			double grandTotal=0;
			
			for(Cart cart: cartList) {
				Prices priceDto = pricesRepository.getPriceDetails(cart.getPid());
				double price = priceDto.getCost() * cart.getQuantity();
				double deliveryCharge = priceDto.getDeliveryCharge() * cart.getQuantity();
				double cartPrice = (price * (100-priceDto.getDiscount())) / 100;
						
				totalPrice += price;
				totalDeliveryCharge += deliveryCharge;
				grandTotal += cartPrice;
				
				cartItems.add(new CartResponseItems(cart.getProductName(), cart.getCategory(), cart.getSellerName(), 
						price, deliveryCharge, cart.getQuantity(), price+deliveryCharge, cartPrice));
			}

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CartResponsePrice(cartItems, totalPrice, totalDeliveryCharge, grandTotal));
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	public ResponseEntity<Map<String, Integer>> getCartCountByUser(String userName){
		Map<String, Integer> body = new HashMap<>();
		body.put("cartCount", cartRepository.totalCartItems(userName));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
	}

}
