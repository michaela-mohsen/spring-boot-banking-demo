package com.banking.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Account;
import com.banking.springboot.model.Customer;
import com.banking.springboot.model.Employee;
import com.banking.springboot.service.impl.AccountServiceImpl;
import com.banking.springboot.service.impl.CustomerServiceImpl;
import com.banking.springboot.service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping
public class AccountController {

	private AccountServiceImpl accountService;
	private CustomerServiceImpl customerService;
	private EmployeeServiceImpl employeeService;

	public AccountController(AccountServiceImpl accountService, CustomerServiceImpl customerService,
			EmployeeServiceImpl employeeService) {
		super();
		this.accountService = accountService;
		this.customerService = customerService;
		this.employeeService = employeeService;
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
		List<Customer> customers = customerService.getAllCustomers();
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("account", account);
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		return "create_account";
	}

	// save new or updated account object
	@PostMapping("/accounts")
	public String saveAccount(@ModelAttribute("account") Account account) {
		accountService.saveAccount(account);
		return "redirect:/accounts";
	}

	// update an account object form
	@GetMapping("/accounts/update/{id}")
	public String updateAccountForm(@PathVariable Integer id, Model model) {
		model.addAttribute("account", accountService.getAccountById(id));
		return "update_account";
	}

	// update an account object
	@PostMapping("/accounts/update/{id}")
	public String updateAccount(@PathVariable Integer id, @ModelAttribute("account") Account account, Model model) {
		// get a specific account
		Account existingAccount = accountService.getAccountById(id);

		// set properties
		existingAccount.setId(account.getId());
		existingAccount.setAvailableBalance(account.getAvailableBalance());
		existingAccount.setPendingBalance(account.getPendingBalance());
		existingAccount.setOpenDate(account.getOpenDate());
		existingAccount.setLastActivityDate(account.getLastActivityDate());
		existingAccount.setCustomer(account.getCustomer());
		existingAccount.setBranch(account.getBranch());
		existingAccount.setEmployee(account.getEmployee());
		existingAccount.setProduct(account.getProduct());

		// save updated account object
		accountService.updateAccount(existingAccount);
		return "redirect:/accounts";
	}

	// delete an account object
	@GetMapping("/accounts/delete/{id}")
	public String deleteAccount(@PathVariable Integer id) {
		accountService.deleteAccountById(id);
		return "redirect:/accounts";
	}

}
