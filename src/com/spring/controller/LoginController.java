/*
 * @(#)LoginController.java Nov 16, 2011
 * 
 * Copyright (c)2004 FlipSwap, Inc.
 * U.S.A. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of FlipSwap, Inc.
 * ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with FlipSwap, Inc.
 */
package com.spring.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.User;


/**
 * 
 * @author tgupta
 * 
 */
@Controller
public class LoginController {

	private MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * @return messageSourceAccessor
	 */
	public MessageSourceAccessor getMessageSourceAccessor() {
		  return messageSourceAccessor;
	}
		 
	/**
	 * @param messageSource the messageSource to set
	 */
	@Resource
	public void setMessageSource(MessageSource messageSource) {
			  this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}
	
	@RequestMapping(value = { "/", "/login**" }, method = RequestMethod.GET)
	public String setupForm(@RequestParam(value = "action", required=false) String action,@RequestParam(value = "logout", required = false) String logout,Model model,  HttpServletRequest request) {
		User user = new User();
		model.addAttribute("command", user);
		if(action!=null){
			model.addAttribute("error", messageSourceAccessor.getMessage("error.invalid.username.password"));
		}
		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		return "login";
	}
	
	@RequestMapping(value = {"/hello**" }, method = RequestMethod.GET)
	public String helloPage(Model model,  HttpServletRequest request) {

		model.addAttribute("title", "Spring Security Custom Login Form");
		model.addAttribute("message", "This is welcome page!");
		return "hello";

	}
}