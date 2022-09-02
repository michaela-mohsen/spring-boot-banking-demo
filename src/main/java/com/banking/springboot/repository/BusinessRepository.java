package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.springboot.model.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

}
