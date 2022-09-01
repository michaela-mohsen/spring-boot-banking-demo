package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Transaction;

@Component
public interface TransactionService {

	List<Transaction> getAllTransactions();
	Transaction saveTransaction(Transaction transaction);
	void deleteTransactionById(Long id);
}
