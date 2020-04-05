package com.example.LoanApplicationUsingSpringBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LoanApplicationUsingSpringBatch.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
