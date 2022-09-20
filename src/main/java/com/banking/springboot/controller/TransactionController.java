package com.banking.springboot.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banking.springboot.model.Transaction;
import com.banking.springboot.service.impl.TransactionServiceImpl;

@Controller
@RequestMapping
public class TransactionController {

    private TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{pageNo}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String listAllTransactions(Model model) {
        return findPaginated(1, "id", "asc", model);
    }
}
