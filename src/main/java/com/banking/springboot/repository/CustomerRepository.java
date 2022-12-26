package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.springboot.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
