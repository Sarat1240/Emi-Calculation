package com.example.LoanApplicationUsingSpringBatch.dto;

import com.example.LoanApplicationUsingSpringBatch.entity.User;

public class LoanDto {

	private Integer loanId;

	private Integer loanAccountNumber;

	private Integer loanAmount;

	private Integer tenure;

	private String bankName;

	private String ifsc;

	private Double rateOfInterest;

	private double emiAmt;

	private Integer uid;
	
	private Integer installmentCount;

	public double getEmiAmt() {
		return emiAmt;
	}

	public void setEmiAmt(double emiAmt) {
		this.emiAmt = emiAmt;
	}

	public Integer getLoanId() {
		return loanId;
	}



	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(Integer loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	

	public Integer getInstallmentCount() {
		return installmentCount;
	}
	
	public void setInstallmentCount(Integer installmentCount) {
		this.installmentCount = installmentCount;
	}

}
