package com.sachin.vastravibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.vastravibe.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
