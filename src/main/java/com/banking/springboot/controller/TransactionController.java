package com.banking.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banking.springboot.model.Account;
import com.banking.springboot.model.Transaction;
import com.banking.springboot.service.impl.AccountServiceImpl;
import com.banking.springboot.service.impl.TransactionServiceImpl;

@Controller
@RequestMapping
public class TransactionController {

    private final TransactionServiceImpl transactionService;
    private final AccountServiceImpl accountService;

    public TransactionController(TransactionServiceImpl transactionService, AccountServiceImpl accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping("/transactions/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 10;

        Page<Transaction> page = transactionService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Transaction> listTransactions = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listTransactions", listTransactions);
        return "transactions";

    }

    @GetMapping("/transactions")
    public String listAllTransactions(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/transactions/new/{id}")
    public String createTransactionForm(@PathVariable Integer id, Model model) {
        Transaction transaction = new Transaction();
        Account existingAccount = accountService.getAccountById(id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("account", existingAccount);

        return "create_transaction";
    }

    @PostMapping("/transactions/new/{id}")
    public String createTransaction(@PathVariable Integer id,
            @ModelAttribute("transaction") Transaction transaction, @ModelAttribute("account") Account account,
            Model model) {

        Account existingAccount = accountService.getAccountById(id);

        if (transaction.getType().equalsIgnoreCase("Deposit")) {
            transactionService.saveTransaction(transaction);
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Double newBalance = existingAccount.getAvailableBalance() + transaction.getAmount();
            existingAccount.setAvailableBalance(newBalance);
            existingAccount.setPendingBalance(newBalance);
            existingAccount.setTransactions(transactions);
            accountService.updateAccount(existingAccount);
        } else if (transaction.getType().equalsIgnoreCase("Withdrawal")) {
            transactionService.saveTransaction(transaction);
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Double newBalance = existingAccount.getAvailableBalance() - transaction.getAmount();
            existingAccount.setAvailableBalance(newBalance);
            existingAccount.setPendingBalance(newBalance);
            existingAccount.setTransactions(transactions);
            accountService.saveAccount(existingAccount);
        }

        return "redirect:/transactions";
    }

    @PostMapping("/transactions")
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }
}
