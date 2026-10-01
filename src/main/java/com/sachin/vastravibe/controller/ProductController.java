package com.sachin.vastravibe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.vastravibe.dto.ProductDto;
import com.sachin.vastravibe.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
	
	private ProductService productService;
	
	//build add Product rest api
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto)
	{
		
		ProductDto savedProduct = productService.createProduct(productDto);
		return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
		
		
	}

	
}
