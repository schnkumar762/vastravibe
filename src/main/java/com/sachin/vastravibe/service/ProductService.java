package com.sachin.vastravibe.service;

import java.util.List;

import com.sachin.vastravibe.dto.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto) ;
	
	 // Read
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);
    
    // Update
    ProductDto updateProduct(Long id, ProductDto productDto);

    // Delete
    void deleteProductById(Long id);
    
    
//Delete all
    void deleteAllProducts();
    
    
    
		
	
}
