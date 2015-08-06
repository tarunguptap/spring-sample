package com.spring.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.UserDAO;
import com.spring.domain.User;

//@Repository("userDAO")
@Component("userDAO")
public class UserDAOImpl extends BaseDAOImpl<User, Long> implements UserDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	/*
	* (non-Javadoc)
	*
	* @see com.spring.dao.impl.BaseDAOImpl#getDataClass()
	*/
	@Override
	protected Class getDataClass() {
		return User.class;
	}
	
	@Override
	public User findByUserName(String userName) {
		try {
			System.out.println(userName);
			User user = (User)getSession().createQuery("from User where username = :userName").setParameter("userName",userName).uniqueResult();
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
