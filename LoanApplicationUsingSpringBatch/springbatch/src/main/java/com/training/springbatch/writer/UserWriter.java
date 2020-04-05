package com.training.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.training.springbatch.entity.User;

public class UserWriter implements ItemWriter<User>{

	@Override
	public void write(List<? extends User> items) throws Exception {
		items.forEach(System.out::println);
		
	}

}
