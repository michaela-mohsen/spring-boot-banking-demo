package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.banking.springboot.model.Branch;

@Component
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
