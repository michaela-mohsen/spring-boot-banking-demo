package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.banking.springboot.model.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
