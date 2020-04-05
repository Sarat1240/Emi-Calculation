package com.training.springbatch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LoanDetails")
public class LoanDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanId;

	private Integer loanAccountNumber;

	private String loanAmount;

	private Integer noOfYears;

	private Integer tenure;
	
	private String bankName;
	
	private String location;
	
	private String ifsc;
	
	private Double rateOfInterest;
	
	private double emiAmt;
	
	@ManyToOne
	@JoinColumn(name="uid")
	public User user;

	
	public double getEmiAmt() {
		return emiAmt;
	}

	public void setEmiAmt(double emiAmt) {
		this.emiAmt = emiAmt;
	}

	public Integer getLoanId() {
		return loanId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(Integer noOfYears) {
		this.noOfYears = noOfYears;
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

	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	@Override
	public String toString() {
		return "LoanDetails [loanId=" + loanId + ", loanAccountNumber=" + loanAccountNumber + ", loanAmount="
				+ loanAmount + ", noOfYears=" + noOfYears + ", tenure=" + tenure + ", bankName=" + bankName
				+ ", location=" + location + ", ifsc=" + ifsc + ", rateOfInterest=" + rateOfInterest + ", emiAmt="
				+ emiAmt + ", user=" + user + "]";
	}

	
	
	
}
