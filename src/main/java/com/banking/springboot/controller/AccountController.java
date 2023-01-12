package com.banking.springboot.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Account;
import com.banking.springboot.model.Branch;
import com.banking.springboot.model.Customer;
import com.banking.springboot.model.Employee;
import com.banking.springboot.model.Product;
import com.banking.springboot.service.impl.AccountServiceImpl;
import com.banking.springboot.service.impl.BranchServiceImpl;
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
	private BranchServiceImpl branchService;

	public AccountController(AccountServiceImpl accountService, CustomerServiceImpl customerService,
			EmployeeServiceImpl employeeService, ProductServiceImpl productService, BranchServiceImpl branchService) {
		super();
		this.accountService = accountService;
		this.customerService = customerService;
		this.employeeService = employeeService;
		this.productService = productService;
		this.branchService = branchService;
	}

	// get all accounts
	@GetMapping("/accounts")
	public String listAccounts(Model model) {

		List<Account> accounts = accountService.getAllAccounts();
		model.addAttribute("accounts", accounts);
		return "accounts";
	}

	@GetMapping("/accounts/type/{type}")
	public String listAccountsByProductType(@Param("type") String type, Model model) {
		String loan = "LOAN";
		String account = "ACCOUNT";

		if (type.equals(loan)) {
			List<Account> accountsByProductLoan = accountService.getAccountsByProductType("LOAN");
			model.addAttribute("accounts", accountsByProductLoan);
			model.addAttribute("type", type);
		} else if (type.equals(account)) {
			List<Account> accountsByProductAccount = accountService.getAccountsByProductType("ACCOUNT");
			model.addAttribute("accounts", accountsByProductAccount);
			model.addAttribute("type", type);
		}
		return "accounts";
	}

	// create a new account object
	@GetMapping("/accounts/new")
	public String createAccount(Model model) {
		Account account = new Account();
		List<Product> products = productService.getAllProducts();
		List<Customer> customers = customerService.getAllCustomers();
		List<Employee> employees = employeeService.getAllEmployees();
		List<Branch> branches = branchService.getAllBranches();

		model.addAttribute("account", account);
		model.addAttribute("products", products);
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		model.addAttribute("branches", branches);
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
			List<Branch> branches = branchService.getAllBranches();
			model.addAttribute("products", products);
			model.addAttribute("customers", customers);
			model.addAttribute("employees", employees);
			model.addAttribute("branches", branches);
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
