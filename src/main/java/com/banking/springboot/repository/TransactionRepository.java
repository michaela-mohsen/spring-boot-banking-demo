package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.banking.springboot.model.Transaction;

@Component
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
