package com.training.springbatch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.training.springbatch.entity.User;

public class UserMapper implements FieldSetMapper<User> {

	@Override
	public User mapFieldSet(FieldSet fieldSet) throws BindException {
		User user=new User();
		//emp.setEmpno(fieldSet.readInt("EMPNO"));
		//user.setName(fieldSet.readString(1));
		//emp.setSalary(fieldSet.readInt(2));
		//emp.setLocation(fieldSet.readString(3));
		//emp.setGendar(fieldSet.readString(4));
		
		return user;
		
	}
	
	

}
