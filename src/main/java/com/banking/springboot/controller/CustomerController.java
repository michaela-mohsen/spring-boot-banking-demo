package com.banking.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Business;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public String listCustomers(Model model) {
        model.addAttribute("individuals", customerService.getAllIndividuals());
        model.addAttribute("businesses", customerService.getAllBusinesses());
        return "customers";
    }

    @GetMapping("/customers/individuals/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createIndividual(Model model) {
        Individual individual = new Individual();
        model.addAttribute("individual", individual);
        return "create_individual";
    }

    @GetMapping("/customers/businesses/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createBusiness(Model model) {
        Business business = new Business();
        model.addAttribute("business", business);
        return "create_business";
    }

    @GetMapping("/customers/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create_customer";
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveIndividual(@ModelAttribute("individual") Individual individual,
            @ModelAttribute("business") Business business, Model model) {
        model.addAttribute("individual", individual);
        model.addAttribute("business", business);
        customerService.saveIndividual(individual);
        customerService.saveBusiness(business);
        return "redirect:/customers";
    }

    @PostMapping("/customers/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        customerService.saveCustomer(customer);
        model.addAttribute("customer", customer);
        return "save_customer";
    }
}
