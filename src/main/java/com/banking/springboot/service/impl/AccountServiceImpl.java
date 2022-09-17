package com.banking.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.banking.springboot.model.Account;
import com.banking.springboot.model.Customer;
import com.banking.springboot.repository.AccountRepository;
import com.banking.springboot.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id).get();
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void deleteAccountById(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public List<Account> getAccountByCustomer(Customer customer) {
		return accountRepository.findAccountByCustomerId(customer);
	}

}
