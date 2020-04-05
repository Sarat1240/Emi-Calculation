package com.training.springbatch.service;

import java.util.List;
import java.util.Optional;

import com.training.springbatch.entity.LoanDetails;
import com.training.springbatch.entity.User;
import com.training.springbatch.model.LoanRequest;

public interface LoanService {
	
	public LoanDetails saveLoan(LoanRequest request);
	
	public List<User> fetLoans();
	
	public Optional<User> fetchLoan(Integer uid);

}
