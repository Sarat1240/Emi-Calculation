package com.example.LoanApplicationUsingSpringBatch.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoanApplicationUsingSpringBatch.dto.LoanDto;
import com.example.LoanApplicationUsingSpringBatch.entity.Loan;
import com.example.LoanApplicationUsingSpringBatch.service.LoanService;



@RestController
@RequestMapping("/loans")
public class LoanController 
{
	@Autowired
	public LoanService service;
		
	@PostMapping
	public ResponseEntity<?> saveLoanReq(@Valid @RequestBody LoanDto loanReq) {
		Loan loanDetails = service.saveLoan(loanReq);
		return ResponseEntity.ok().body(loanDetails);
	}
	
	@GetMapping
	public ResponseEntity<List<Loan>> getAllLoanDetails(){
		List<Loan> response = service.fetchAllLoansData();
		System.out.println(response);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getLoanDetails(@PathVariable("userId") Integer userId){
		Loan response = service.fetchLoanDetails(userId);
		if(response!=null)
			return ResponseEntity.ok().body(response);
		else
			return ResponseEntity.notFound().build();
	}

}
