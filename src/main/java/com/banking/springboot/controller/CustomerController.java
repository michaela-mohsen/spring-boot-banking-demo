package com.banking.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Customer;
import com.banking.springboot.model.Individual;
import com.banking.springboot.service.impl.CustomerServiceImpl;

@Controller
@RequestMapping
public class CustomerController {

    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String listCustomers(Model model) {
        model.addAttribute("individuals", customerService.getAllIndividuals());
        return "customers";
    }

    @GetMapping("/customers/individuals/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String createIndividual(Model model) {
        Individual individual = new Individual();
        model.addAttribute("individual", individual);
        return "create_individual";
    }

    @GetMapping("/customers/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String createCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create_customer";
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveIndividual(@ModelAttribute("individual") Individual individual, Model model) {
        model.addAttribute("individual", individual);
        customerService.saveIndividual(individual);
        return "redirect:/customers";
    }

    @PostMapping("/customers/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        customerService.saveCustomer(customer);
        model.addAttribute("customer", customer);
        return "save_customer";
    }
}
