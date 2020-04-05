package com.training.springbatch.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.springbatch.entity.LoanDetails;
import com.training.springbatch.entity.User;
import com.training.springbatch.model.LoanRequest;
import com.training.springbatch.service.LoanServiceImpl;


@RestController
@RequestMapping("/api/loan")
public class LoanController {
	
	@Autowired
	public LoanServiceImpl service;
	
	@PostMapping
	public ResponseEntity<?> saveLoanData(@Valid @RequestBody LoanRequest request) {
		LoanDetails loanDetails = service.saveLoan(request);
		return ResponseEntity.ok().body(loanDetails);
	}
	
	@GetMapping
	public ResponseEntity<?> fetchLoanDetails(){
		List<User> response = service.fetLoans();
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/loanData/{userId}")
	public ResponseEntity<Optional<User>> fetchLoan(@PathVariable("userId") Integer userId){
		Optional<User> response = service.fetchLoan(userId);
		return ResponseEntity.ok().body(response);
	}

}
