package com.sachin.vastravibe.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


// dto to transfer data between client and server
public class ProductDto {
	
	private Long id;
	private String productName;
	private String image;
	private int price;
	

public ProductDto(Long id,String productName,String image,int price){
	this.id=id;
	this.productName=productName;
	this.image=image;
	this.price=price;
	
}


}
