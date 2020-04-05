package com.training.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.springbatch.entity.LoanDetails;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer>{

}
