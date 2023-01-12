package com.banking.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.banking.springboot.model.Account;

@Component
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select a from Account a join Product p on a.product = p.id where p.type = ?1")
    List<Account> findByProductType(String type);
}
