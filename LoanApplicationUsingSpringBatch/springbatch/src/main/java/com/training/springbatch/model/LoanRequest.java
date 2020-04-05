package com.training.springbatch.model;

public class LoanRequest {

	private Integer loanAccountNumber;

	private Integer loanAmount;

	private Double rateOfInterest;

	private Integer tenure;

	private String bankName;

	private String location;

	private String ifsc;

	private Integer uid;

	private double emiAmt;

	public Integer getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(Integer loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public double getEmiAmt() {
		return emiAmt;
	}

	public void setEmiAmt(double emiAmt) {
		this.emiAmt = emiAmt;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	

	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
