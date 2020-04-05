package com.example.LoanApplicationUsingSpringBatch.service;

import java.util.List;

import com.example.LoanApplicationUsingSpringBatch.dto.LoanDto;
import com.example.LoanApplicationUsingSpringBatch.entity.Loan;

public interface LoanService {
	
	public Loan saveLoan(LoanDto req);
	public List<Loan> fetchAllLoansData();
	public Loan fetchLoanDetails(Integer userId);
	public void updateLoanData(Integer curLoanAmount, Integer currentInstCount, Double emi);
}
