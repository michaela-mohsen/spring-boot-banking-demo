package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.banking.springboot.model.Customer;

@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
