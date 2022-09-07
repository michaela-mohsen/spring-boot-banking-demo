package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Business;
import com.banking.springboot.model.Customer;
import com.banking.springboot.model.Individual;

@Component
public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer getCustomerById(Long id);

	Customer saveCustomer(Customer customer);

	void deleteCustomerById(Long id);

	Customer updateCustomer(Customer customer);

	List<Individual> getAllIndividuals();

	Individual saveIndividual(Individual individual);

	List<Business> getAllBusinesses();

	Business saveBusiness(Business business);
}
