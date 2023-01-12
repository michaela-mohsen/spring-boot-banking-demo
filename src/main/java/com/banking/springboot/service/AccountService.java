package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Account;

@Component
public interface AccountService {
	List<Account> getAllAccounts();

	List<Account> getAccountsByProductType(String type);

	Account getAccountById(Integer id);

	Account updateAccount(Account account);

	void deleteAccountById(Integer id);

	Account saveAccount(Account account);

}
