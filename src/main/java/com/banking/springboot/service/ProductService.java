package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Product;

@Component
public interface ProductService {
	List<Product> getAllProducts();

}
