package com.spring.dao;

import com.spring.domain.User;


public interface UserDAO extends DAO<User,Long>{
	public User findByUserName(String userName);
}
