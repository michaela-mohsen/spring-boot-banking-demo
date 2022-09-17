package com.banking.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.banking.springboot.model.Account;
import com.banking.springboot.model.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("Select a from Account a join fetch a.customer where a.customer=?1")
    public List<Account> findAccountByCustomerId(Customer customer);

}
