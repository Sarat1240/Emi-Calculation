package com.training.springbatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.springbatch.constants.LoanDetailsConstant;
import com.training.springbatch.entity.LoanDetails;
import com.training.springbatch.entity.User;
import com.training.springbatch.model.LoanRequest;
import com.training.springbatch.repository.LoanDetailsRepository;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	public LoanDetailsRepository loanDao;

	@Autowired
	public UserRepository userDao;

	@Override
	public LoanDetails saveLoan(LoanRequest request) {
		LoanDetails mappedData = mapLoanData(request);
		System.out.println("LoanDetails before inserting into DB:"+mappedData);
		LoanDetails response = loanDao.save(mappedData);
		System.out.println("LoanDetails after inserting into DB:"+response);
		return response;

	}

	public LoanDetails mapLoanData(LoanRequest request) {
		Optional<User> userRes = userDao.findById(request.getUid());
		LoanDetails loanDetails = new LoanDetails();
		if (request != null) {
			userRes.ifPresent(user -> {
				if (StringUtils.isNotBlank(request.getBankName()))
					loanDetails.setBankName(request.getBankName());
				if (request.getLoanAccountNumber() != null)
					loanDetails.setLoanAccountNumber(request.getLoanAccountNumber());
				if (request.getLoanAmount() != null)
					loanDetails.setLoanAmount("-"+request.getLoanAmount());
				if (request.getTenure() != null)
					loanDetails.setTenure(request.getTenure());
				if (request.getRateOfInterest() !=null)
					loanDetails.setRateOfInterest(request.getRateOfInterest());
				calculateLoanAmount(loanDetails, request);
				loanDetails.setUser(user);
			});

		}
		return loanDetails;
	}

	@Override
	public List<User> fetLoans() {
		return userDao.findAll();
	}

	public void calculateLoanAmount(LoanDetails loanDetails, LoanRequest request) {
		double emi;
		Integer loanAmt = request.getLoanAmount();
		//Float rate = request.getRateOfInterest();
		Double rate=LoanDetailsConstant.RATE_OF_INTEREST;
		Integer tenure = request.getTenure();
		rate = rate / (12 * 100);
		tenure = tenure * 12;
		emi = (loanAmt * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
		loanDetails.setEmiAmt(emi);
	}

	@Override
	public Optional<User> fetchLoan(Integer uid) {
		Optional<User> user = userDao.findById(uid);
		if(user.isPresent()) {
			return user;
		}
		else {
			return null;
		}
	}

}
