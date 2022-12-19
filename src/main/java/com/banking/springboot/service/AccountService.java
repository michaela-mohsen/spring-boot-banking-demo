package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Account;

@Component
public interface AccountService {
	List<Account> getAllAccounts();

	Account getAccountById(Long id);

	Account updateAccount(Account account);

	void deleteAccountById(Long id);

	Account saveAccount(Account account);

	List<Account> getAccountByProduct(Long id);

}
