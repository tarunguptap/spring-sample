package com.spring.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.spring.dao.UserDAO;
import com.spring.domain.User;
import com.spring.error.CustomError;
import com.spring.exception.ValidationException;
import com.spring.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserDAO userDAO;
	
	private MessageSourceAccessor messageSourceAccessor;
	
	@Resource
    public void setMessageSource(MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }
	
	@Override
	public void updatePassword(Long userId, String newPassword) {
		User user = userDAO.findByPK(userId);
		user.setPassword(newPassword);
        userDAO.saveOrUpdate(user);
	}
	
	@Override
	public User findUserById(Long userId) {
		User user = userDAO.findByPK(userId);
		if(user == null) {
			throw new ValidationException(CustomError.ErrorCode.USER_NOT_FOUND.getErrorCode(),
                    messageSourceAccessor.getMessage("error.user.not.found"));
		}
		return user;
	}
	
}