package com.banking.springboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.springboot.model.Account;
import com.banking.springboot.service.impl.AccountServiceImpl;

@Controller
@RequestMapping
public class AccountController {

	private AccountServiceImpl accountService;

	public AccountController(AccountServiceImpl accountService) {
		super();
		this.accountService = accountService;
	}

	@GetMapping("/accounts")
	public String listAccounts(Model model) {
		return findPaginated(1, model);
	}

	@GetMapping("/accounts/new")
	public String createAccount(Model model) {
		Account account = new Account();
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("account", account);
		return "create_account";
	}

	@PostMapping("/accounts")
	public String saveAccount(@ModelAttribute("account") Account account) {
		accountService.saveAccount(account);
		return "redirect:/accounts";
	}

	@GetMapping("/accounts/update/{id}")
	public String updateAccountForm(@PathVariable Long id, Model model) {
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("account", accountService.getAccountById(id));
		return "update_account";
	}

	@PostMapping("/accounts/{id}")
	public String updateAccount(@PathVariable Long id, @ModelAttribute("account") Account account, Model model) {
		Account existingAccount = accountService.getAccountById(id);
		existingAccount.setId(account.getId());
		existingAccount.setAvailableBalance(account.getAvailableBalance());
		existingAccount.setPendingBalance(account.getPendingBalance());
		existingAccount.setOpenDate(account.getOpenDate());
		existingAccount.setLastActivityDate(account.getLastActivityDate());
		existingAccount.setCustomer(account.getCustomer());
		existingAccount.setBranch(account.getBranch());
		existingAccount.setEmployee(account.getEmployee());
		existingAccount.setProduct(account.getProduct());

		accountService.updateAccount(existingAccount);
		return "redirect:/accounts";
	}

	@GetMapping("/{id}")
	public String deleteAccount(@PathVariable Long id) {
		accountService.deleteAccountById(id);
		return "redirect:/accounts";
	}

	@GetMapping("/accounts/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 10;

		Page<Account> page = accountService.findPaginated(pageNo, pageSize);
		List<Account> listAccounts = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTransactions", listAccounts);
		return "accounts";
	}

}
