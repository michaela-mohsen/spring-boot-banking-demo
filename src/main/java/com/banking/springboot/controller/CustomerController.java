package com.banking.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Customer;
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
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/new")
    @PreAuthorize("hasRole('USER')")
    public String createCustomer(Model model) {
        Customer customer = new Customer();
        List<String> states = customerService.listAllStates();
        model.addAttribute("customer", customer);
        model.addAttribute("states", states);
        return "create_customer";
    }

    @PostMapping("/customers/save")
    @PreAuthorize("hasRole('USER')")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult,
            Model model) {
        if (!bindingResult.hasErrors()) {
            customerService.saveCustomer(customer);
            model.addAttribute("customer", customer);
            return "save_customer";
        } else {
            List<String> states = customerService.listAllStates();
            model.addAttribute("states", states);
            model.addAttribute("customer", customer);
            model.addAttribute("bindingResult", bindingResult);
            return "create_customer";
        }

    }
}
