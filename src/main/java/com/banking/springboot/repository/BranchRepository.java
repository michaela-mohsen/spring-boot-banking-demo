package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.springboot.model.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
