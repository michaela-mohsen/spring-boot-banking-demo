package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.banking.springboot.model.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
