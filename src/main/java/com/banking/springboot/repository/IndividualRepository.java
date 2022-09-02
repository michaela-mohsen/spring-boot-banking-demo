package com.banking.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.springboot.model.Individual;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {

}
