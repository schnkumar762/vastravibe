package com.sachin.vastravibe.service.impl;

import org.springframework.stereotype.Service;

import com.sachin.vastravibe.dto.ProductDto;
import com.sachin.vastravibe.entity.Product;
import com.sachin.vastravibe.mapper.ProductMapper;
import com.sachin.vastravibe.repository.ProductRepository;
import com.sachin.vastravibe.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		
		Product product = ProductMapper.mapToProduct(productDto);
	Product savedProduct = productRepository.save(product);
	return ProductMapper.mapToProductDto(savedProduct);
	}

}
