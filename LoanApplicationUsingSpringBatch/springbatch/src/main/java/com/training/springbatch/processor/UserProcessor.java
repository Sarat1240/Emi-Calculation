package com.training.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.training.springbatch.entity.User;

public class UserProcessor implements ItemProcessor<User, User> {

	
	@Override
	public User process(User item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
