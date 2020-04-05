package com.training.springbatch.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.springbatch.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
