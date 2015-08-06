package com.spring.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.Role;
import com.spring.domain.User;
import com.spring.domain.enums.RoleType;
import com.spring.error.CustomError;
import com.spring.exception.ValidationException;
import com.spring.propertyeditor.CustomEnumEditor;
import com.spring.security.util.SecurityUtils;
import com.spring.service.UserService;


@Controller
@RequestMapping({"/userAdmin**"})
public class UserAdminController
{
	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);
	
	@Resource
	UserService userService;
	
	private MessageSourceAccessor messageSourceAccessor;
	
	@Resource
    public void setMessageSource(MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(RoleType.class, new CustomEnumEditor(RoleType.class));
	}
	
  private static final String SAVEUSER = "action=saveUser";
  
  @ModelAttribute("RoleTypeArray")
  public RoleType[] roleTypeArray()
  {
	  RoleType[] roleTypeArray = RoleType.values();
	  System.out.println(roleTypeArray.length);
	  System.out.println(roleTypeArray[0].name());
	  System.out.println(roleTypeArray[0].getKey());
    return roleTypeArray;
  }
  
  
  @RequestMapping(method={RequestMethod.GET})
  public String showForm(@ModelAttribute("user") User userAdminCommand, ModelMap modelMap)
  {
	  User loggedInUser = userService.findUserById(SecurityUtils.getAuthenticatedUser().getId());
	  @SuppressWarnings("unused")
	Set<Role> userRole = loggedInUser.getRoles();
	  modelMap.addAttribute("loggedInUser", loggedInUser);
      System.out.println("Inside user admin showform");
      return "userAdmin";
  }
  
  @RequestMapping(method={RequestMethod.POST}, params={SAVEUSER})
  public String saveUser(@ModelAttribute("user") User userAdminCommand, BindingResult results, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response)
  {
    System.out.println(userAdminCommand.getUsername());
    modelMap.addAttribute("title", "Spring Security Custom Login Form");
    modelMap.addAttribute("message", "This is welcome page!");
    return "hello";
  }
  
  @ExceptionHandler(Exception.class)
  public @ResponseBody CustomError handleRuntimeException(Exception exception, HttpServletResponse httpResponse) {
      logger.error(messageSourceAccessor.getMessage("error.processing"), exception);
      CustomError error = new CustomError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              messageSourceAccessor.getMessage("error.internal.error"));
      httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return error;
  }
  
  @ExceptionHandler(ValidationException.class)
  public @ResponseBody CustomError handleValidationException(ValidationException argumentException,
          HttpServletResponse httpResponse) {
      logger.error(messageSourceAccessor.getMessage("error.validationException"), argumentException);
      CustomError error = new CustomError(argumentException.getCode(), argumentException.getMessage());
      httpResponse.setStatus(HttpServletResponse.SC_OK);
      return error;
  }
}
