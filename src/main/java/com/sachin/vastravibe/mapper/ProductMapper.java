package com.sachin.vastravibe.mapper;

import com.sachin.vastravibe.dto.ProductDto;
import com.sachin.vastravibe.entity.Product;

public class ProductMapper {
	
	public static ProductDto mapToProductDto(Product product) {
		return new ProductDto(
				 product.getId(),
				    product.getProductName(),
				    product.getImage(),
				    product.getPrice()
				
				);
	}
	
	public static Product mapToProduct(ProductDto productDto) {
		Product product = new Product();
		
		  product.setProductName(productDto.getProductName());
		    product.setImage(productDto.getImage());
		    product.setPrice(productDto.getPrice());
		    
		    
		return product;
               
	}

}
