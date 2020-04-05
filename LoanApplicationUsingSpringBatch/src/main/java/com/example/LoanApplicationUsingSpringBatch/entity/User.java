package com.example.LoanApplicationUsingSpringBatch.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer uid;
	private String uname;
	private Integer salary;
	private String gender;
	private Integer age;
	private String pan;
	private String aadhar;
	
	@OneToMany(mappedBy ="user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	public List<Loan> loanList;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	/**
	 * @return the loanList
	 */
	public List<Loan> getLoanList() {
		return loanList;
	}

	/**
	 * @param loanList the loanList to set
	 */
	public void setLoanList(List<Loan> loanList) {
		this.loanList = loanList;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", salary=" + salary + ", gender=" + gender + ", age=" + age
				+ ", pan=" + pan + ", aadhar=" + aadhar + ", loanList=" + loanList + "]";
	}

	
	

	}
