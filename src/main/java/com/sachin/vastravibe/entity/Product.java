package com.sachin.vastravibe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name= "products")

public class Product {
	
	//instance variable
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="PName")
	private String productName;
	
	@Column(name="PImage")
	private String image;
	
	@Column(name="Pprice",nullable = false)
	private int price;
	
	//getter setter Id
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	
	//getter setter productName
	
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName=productName;
		}
		
		// getter setter image

		public String getImage() {
		    return image;
		}

		public void setImage(String image) {
		    this.image = image;
		}
		
		// getter setter price

		public int getPrice() {
		    return price;
		}

		public void setPrice(int price) {
		    this.price = price;
		}
		
		
		//constructor
		
		//no_args
		public Product(){
			productName="";
			image= "";
			price=0;
			
			
		}
	public	Product(String name){
			productName=name;
			image = "";
			price= 0;
			
			
			
		}
	
	public Product(String productName,String image,int price) {
		this.productName= productName;
		this.image = image;
		this.price=price;
	}
		
}
