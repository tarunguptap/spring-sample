package com.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FormController
{
	 @RequestMapping(value ="/welcome", method = RequestMethod.GET) 
	public String showForm()throws Exception
	{
		System.out.println("Inside Show Form Method");
		return	"modsub";
	}
	
}

	
	


