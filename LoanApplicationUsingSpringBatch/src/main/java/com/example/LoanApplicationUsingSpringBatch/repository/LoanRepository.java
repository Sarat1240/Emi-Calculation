package com.example.LoanApplicationUsingSpringBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LoanApplicationUsingSpringBatch.entity.Loan;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	@Query("select l from Loan l where l.user.uid=?1")
	public Loan findByUser(Integer userId);

}
