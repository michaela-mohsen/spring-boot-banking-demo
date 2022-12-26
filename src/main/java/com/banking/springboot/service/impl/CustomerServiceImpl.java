package com.banking.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.springboot.model.Customer;
import com.banking.springboot.model.Individual;
import com.banking.springboot.repository.CustomerRepository;
import com.banking.springboot.repository.IndividualRepository;
import com.banking.springboot.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private IndividualRepository individualRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository, IndividualRepository individualRepository) {
		this.customerRepository = customerRepository;
		this.individualRepository = individualRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(Integer id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Individual> getAllIndividuals() {
		return individualRepository.findAll();
	}

	@Override
	public Individual saveIndividual(Individual individual) {
		return individualRepository.save(individual);
	}

}
