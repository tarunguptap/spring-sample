package com.spring.security.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spring.domain.User;
import com.spring.security.UserAwareUserDetails;

public class SecurityUtils {

	public static final User getAuthenticatedUser() {
		SecurityContext sc = SecurityContextHolder.getContext();

		if (sc == null || sc.getAuthentication() == null)
			return null;

		return ((UserAwareUserDetails) sc.getAuthentication().getPrincipal()).getUser();
	}

}
