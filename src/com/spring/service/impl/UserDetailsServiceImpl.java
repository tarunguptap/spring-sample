package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.UserDAO;
import com.spring.domain.Role;
import com.spring.domain.User;
import com.spring.security.UserAwareUserDetails;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Resource
	UserDAO userDAO;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = null;	
		try {
			user = userDAO.findByUserName(userName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (user == null) {
			logger.error("User with username : " + userName + " did not found");
			throw new UsernameNotFoundException("No user found with username: " + userName);
		}
		List<SimpleGrantedAuthority> roles = getRoles(user.getRoles());
		return new UserAwareUserDetails(user, roles);
	}

	private List<SimpleGrantedAuthority> getRoles(Set<Role> roleSet) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		Iterator<Role> itr = roleSet.iterator();
		while (itr.hasNext()) {
			Role role = (Role) itr.next();
			authorities.add(new SimpleGrantedAuthority(role.getType().toString()));
		}
		return authorities;
	}
}
