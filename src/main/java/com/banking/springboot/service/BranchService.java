package com.banking.springboot.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.springboot.model.Branch;

@Component
public interface BranchService {
	List<Branch> getAllBranches();

	Branch getBranchById(Integer id);
}
