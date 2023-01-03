package com.banking.springboot.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Account;
import com.banking.springboot.model.Customer;
import com.banking.springboot.model.Employee;
import com.banking.springboot.model.Product;
import com.banking.springboot.service.impl.AccountServiceImpl;
import com.banking.springboot.service.impl.CustomerServiceImpl;
import com.banking.springboot.service.impl.EmployeeServiceImpl;
import com.banking.springboot.service.impl.ProductServiceImpl;

@Controller
@RequestMapping
public class AccountController {

	private AccountServiceImpl accountService;
	private CustomerServiceImpl customerService;
	private EmployeeServiceImpl employeeService;
	private ProductServiceImpl productService;

	public AccountController(AccountServiceImpl accountService, CustomerServiceImpl customerService,
			EmployeeServiceImpl employeeService, ProductServiceImpl productService) {
		super();
		this.accountService = accountService;
		this.customerService = customerService;
		this.employeeService = employeeService;
		this.productService = productService;
	}

	// get all accounts
	@GetMapping("/accounts")
	public String listAccounts(Model model) {

		List<Account> accounts = accountService.getAllAccounts();
		model.addAttribute("accounts", accounts);
		return "accounts";
	}

	// create a new account object
	@GetMapping("/accounts/new")
	public String createAccount(Model model) {
		Account account = new Account();
		List<Product> products = productService.getAllProducts();
		List<Customer> customers = customerService.getAllCustomers();
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("account", account);
		model.addAttribute("products", products);
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		return "create_account";
	}

	// save new or updated account object
	@PostMapping("/accounts")
	public String saveAccount(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult,
			Model model) {

		if (!bindingResult.hasErrors()) {
			account.setStatus("ACTIVE");
			account.setOpenDate(new Date());
			account.setLastActivityDate(new Date());
			accountService.saveAccount(account);
			return "redirect:/accounts";
		} else {
			List<Product> products = productService.getAllProducts();
			List<Customer> customers = customerService.getAllCustomers();
			List<Employee> employees = employeeService.getAllEmployees();
			model.addAttribute("products", products);
			model.addAttribute("customers", customers);
			model.addAttribute("employees", employees);
			model.addAttribute("account", account);
			model.addAttribute("bindingResult", bindingResult);
			return "create_account";
		}

	}

	// activate/deactivate an account object
	@GetMapping("/accounts/status/{id}")
	public String toggleAccount(@PathVariable Integer id) {
		Account existingAccount = accountService.getAccountById(id);
		if (existingAccount.getStatus().equalsIgnoreCase("ACTIVE")) {
			existingAccount.setStatus("INACTIVE");
			accountService.saveAccount(existingAccount);
		} else if (existingAccount.getStatus().equalsIgnoreCase("INACTIVE")) {
			existingAccount.setStatus("ACTIVE");
			accountService.saveAccount(existingAccount);
		}
		return "redirect:/accounts";
	}

}
