package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CartDto;
import com.app.dto.LoginDto;
import com.app.dto.ModifyCartDto;
import com.app.dto.ProductsDto;
import com.app.dto.UsersDto;
import com.app.dto.UsersUpdateDto;
import com.app.entity.Products;
import com.app.response.CartResponsePrice;
import com.app.response.ProductResponse;
import com.app.service.CartService;
import com.app.service.ProductService;
import com.app.service.UsersService;

@RestController
public class AppController {

	@Autowired
	ProductService productService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	CartService cartService;

	@GetMapping(value = "/greet")
	public String greet() {
		return "Welcome to the my app";
	}

	@PostMapping(value = "/newProduct")
	public ResponseEntity<ProductsDto> newProduct(@RequestBody ProductsDto productsDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.saveProducts(productsDto));
	}

	@GetMapping(value = "/searchProduct/{productName}")
	public ResponseEntity<List<ProductResponse>> searchProducts(@PathVariable("productName") String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetails(productName));
	}

	@GetMapping(value = "/{productName}/details")
	public ResponseEntity<List<Products>> searchProduct(@PathVariable("productName") String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.serachProduct(productName));
	}

	@PostMapping(value = "/signUp")
	public ResponseEntity<UsersDto> signUp(@RequestBody UsersDto usersDto) {
//			System.out.println("adress: "+ userDto.getUsername()+ userDto.getAddressDto().getCity());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usersService.addUserDetails(usersDto));

	}
	
	@PostMapping(value="/signin")
	public ResponseEntity<Map<String, String>> sigin(@RequestBody LoginDto loginDto){
		return usersService.login(loginDto.getUserName(), loginDto.getPassword());
	}
	
	@PutMapping(value="/{userName}/update")
	public ResponseEntity<UsersUpdateDto> updateUserDetails(@PathVariable("userName") String userName, 
			@RequestBody UsersUpdateDto usersUpdateDto){
		return ResponseEntity.status(HttpStatus.OK).body(usersService.updateCustomer(userName, usersUpdateDto));	
	}
	
	@PostMapping(value="/{userName}/addtocart")
	public ResponseEntity<CartDto> addToCart(@PathVariable("userName") String userName, @RequestBody CartDto cartDto) {
		return cartService.addToCart(userName, cartDto);
	}
	
	@PutMapping(value="/{userName}/modifycart")
	public ResponseEntity modifyCart(@PathVariable("userName") String userName, @RequestBody ModifyCartDto modifyCartDto) {
		modifyCartDto.setUserName(userName);
		return cartService.modifyCart(modifyCartDto);
	}
	
	@GetMapping(value="/{userName}/cart")
	public ResponseEntity<CartResponsePrice> cartDetails(@PathVariable("userName") String userName) {
		return cartService.viewCartByUser(userName);
	}
	
	@GetMapping(value="/{userName}/cartcount")
	public ResponseEntity<Map<String, Integer>>  getCartCount(@PathVariable("userName") String userName){
		return cartService.getCartCountByUser(userName);
	}

}
