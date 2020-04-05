package com.example.LoanApplicationUsingSpringBatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LoanApplicationUsingSpringBatch.constant.LoanConstant;
import com.example.LoanApplicationUsingSpringBatch.dto.LoanDto;
import com.example.LoanApplicationUsingSpringBatch.entity.Loan;
import com.example.LoanApplicationUsingSpringBatch.entity.User;
import com.example.LoanApplicationUsingSpringBatch.repository.LoanRepository;
import com.example.LoanApplicationUsingSpringBatch.repository.UserRepository;

import io.micrometer.core.instrument.util.StringUtils;


@Service
@Transactional
public class LoanServiceImpl implements LoanService {
	
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Loan saveLoan(LoanDto req) {
		Loan mappedData = mapLoanData(req);
		System.out.println("LoanDetails before inserting into DB:"+mappedData);
		Loan response = loanRepository.save(mappedData);
		System.out.println("LoanDetails after inserting into DB:"+response);
		return response;
	}

	private Loan mapLoanData(LoanDto req) {
		Optional<User> userRes = userRepository.findById(req.getUid());
		Loan loanDetails = new Loan();
		
		userRes.ifPresent(user -> {
			if (StringUtils.isNotBlank(req.getBankName()))
				loanDetails.setBankName(req.getBankName());
			if (req.getLoanAccountNumber() != null)
				loanDetails.setLoanAccountNumber(req.getLoanAccountNumber());
			if (req.getLoanAmount() != null)
			{
				loanDetails.setInitialLoanAmount("-"+req.getLoanAmount());
				loanDetails.setCurrentLoanAmount(req.getLoanAmount());
			}
			
			if (req.getTenure() != null)
				loanDetails.setTenure(req.getTenure());
			if (req.getRateOfInterest() !=null)
				loanDetails.setRateOfInterest(req.getRateOfInterest());
			if (req.getIfsc() !=null)
				loanDetails.setIfsc(req.getIfsc());
			calculateLoanAmount(loanDetails, req);
			loanDetails.setUser(user);
		});


	return loanDetails;
	}

	private void calculateLoanAmount(Loan loanDetails, LoanDto req) {
		double emi;
		Integer loanAmt = req.getLoanAmount();
		//Float rate = request.getRateOfInterest();
		Double rate=LoanConstant.RATE_OF_INTEREST;
		Integer tenure = req.getTenure();
		rate = rate / (12 * 100);
		tenure = tenure * 12;
		emi = (loanAmt * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
		loanDetails.setEmiAmt(emi);
		loanDetails.setRateOfInterest(rate);
		
	}

	@Override
	public List<Loan> fetchAllLoansData() {
		return loanRepository.findAll();
		 
	}

	@Override
	public Loan fetchLoanDetails(Integer userId) {
 		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return loanRepository.findByUser(userId);
		}
		else {
			return null;
		}
	}

	@Override
	public void updateLoanData(Integer curLoanAmount, Integer currentInstCount, Double emi) {
		// TODO Auto-generated method stub
		Loan l =  new Loan();
		l.setCurrentLoanAmount(curLoanAmount);
		l.setInstallmentCount(currentInstCount+1);
		loanRepository.save(l);
		
	}	
}
