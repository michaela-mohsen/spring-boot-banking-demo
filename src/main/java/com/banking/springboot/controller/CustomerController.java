package com.banking.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Business;
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
    public String listCustomers(Model model) {
        model.addAttribute("individuals", customerService.getAllIndividuals());
        model.addAttribute("businesses", customerService.getAllBusinesses());
        return "customers";
    }

    @GetMapping("/customers/individuals/new")
    public String createIndividual(Model model) {
        Individual individual = new Individual();
        model.addAttribute("individual", individual);
        return "create_individual";
    }

    @GetMapping("/customers/businesses/new")
    public String createBusiness(Model model) {
        Business business = new Business();
        model.addAttribute("business", business);
        return "create_business";
    }
}
