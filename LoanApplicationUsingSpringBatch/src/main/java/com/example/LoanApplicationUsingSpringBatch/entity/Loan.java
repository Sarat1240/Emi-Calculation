package com.example.LoanApplicationUsingSpringBatch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanId;

	private Integer loanAccountNumber;

	private String InitialLoanAmount;

	private Integer tenure;
	
	private String bankName;
	
	private String ifsc;
	
	private Double rateOfInterest;
	
	private double emiAmt;
	
	private Integer installmentCount;
	
	private Integer currentLoanAmount;
	
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


		/**
	 * @return the installmentCount
	 */
	public Integer getInstallmentCount() {
		return installmentCount;
	}

	/**
	 * @param installmentCount the installmentCount to set
	 */
	public void setInstallmentCount(Integer installmentCount) {
		this.installmentCount = installmentCount;
	}

	/**
	 * @return the initialLoanAmount
	 */
	public String getInitialLoanAmount() {
		return InitialLoanAmount;
	}

	/**
	 * @param initialLoanAmount the initialLoanAmount to set
	 */
	public void setInitialLoanAmount(String initialLoanAmount) {
		InitialLoanAmount = initialLoanAmount;
	}

	/**
	 * @return the currentLoanAmount
	 */
	public Integer getCurrentLoanAmount() {
		return currentLoanAmount;
	}

	/**
	 * @param currentLoanAmount the currentLoanAmount to set
	 */
	public void setCurrentLoanAmount(Integer currentLoanAmount) {
		this.currentLoanAmount = currentLoanAmount;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanAccountNumber=" + loanAccountNumber + ", InitialLoanAmount="
				+ InitialLoanAmount + ", tenure=" + tenure + ", bankName=" + bankName + ", ifsc=" + ifsc
				+ ", rateOfInterest=" + rateOfInterest + ", emiAmt=" + emiAmt + ", installmentCount=" + installmentCount
				+ ", currentLoanAmount=" + currentLoanAmount + ", user=" + user + "]";
	}

	
	
	
	
	
}
