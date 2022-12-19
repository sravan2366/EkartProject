package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ProductsDto;
import com.app.entity.Products;
import com.app.repository.ProductsRepository;
import com.app.response.ProductResponse;

@Service
public class ProductService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ProductsRepository productsRepository;
	
	public ProductsDto saveProducts(ProductsDto productsDto) {
		productsRepository.saveAndFlush(modelMapper.map(productsDto, Products.class));
		return productsDto;	
	}
	
	public List<ProductResponse> getProductDetails(String productName){
		return productsRepository.getProductDetails(productName);
	}
	
	public List<Products> serachProduct(String productName){
		return productsRepository.findByName(productName);
	}
}
