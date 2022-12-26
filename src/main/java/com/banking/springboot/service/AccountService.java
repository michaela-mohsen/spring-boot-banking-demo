package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Account;
import com.banking.springboot.model.Product;

@Component
public interface AccountService {
	List<Account> getAllAccounts();

	Account getAccountById(Integer id);

	Account updateAccount(Account account);

	void deleteAccountById(Integer id);

	Account saveAccount(Account account);

	List<Account> getAccountByProduct(Product product, Integer id);

}
