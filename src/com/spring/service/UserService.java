package com.spring.service;

import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.User;


@Transactional(readOnly = true)
public interface UserService 
{
	@Transactional(readOnly = false)
	public void updatePassword(Long userId, String newPassword);
	
	public User findUserById(Long userId);
	
}