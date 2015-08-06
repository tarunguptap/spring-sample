package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bean.example.UserValidationBean;
import com.spring.domain.ModelSub;
import com.spring.enums.example.UserValidationType;
import com.spring.service.SubService;
import com.spring.util.ControllerUtils;
import com.spring.validator.ModSubValidator;
import com.spring.validator.ValidatorAdapter;
import com.spring.validator.example.UserValidator;

@Controller
@RequestMapping("/submit")
public class SubActController  
{
	private static final String SAVEUSER = "oper=saveUser";
	private static final String EDITUSER = "oper=editUser";
	private static final String DELUSER = "oper=deleteUser";
	public static final String REDIRECT_LOGIN_VIEW = "redirect:submit";
	
	@Resource
	private SubService subService;
	
	@Resource
	private ModSubValidator modSubValidator;
	
	@Resource
    protected List<UserValidator> userValidatorList;
	
	public String loginView ="modsub";
	
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
	
	@RequestMapping(method = RequestMethod.GET)
	public String subActView(Model model)
	{
		
		System.out.println("Inside subActView");
		return	loginView;
	}
	
	@RequestMapping(method = RequestMethod.POST, params={SAVEUSER})
	public String saveUser(@ModelAttribute("submitform")ModelSub modsub,BindingResult errors,ModelMap modelMap, HttpServletRequest request)throws Exception
	{
		
		
		// This an example of validation pattern
		String userName = ServletRequestUtils.getRequiredStringParameter(request,"uname");
		BindException exceptions = new BindException(userValidatorList, userName);
		
		List<UserValidationType> skipValidationType = new ArrayList<UserValidationType>();
		skipValidationType.add(UserValidationType.APIVAL);
		
		UserValidationBean userValidationBean = new UserValidationBean();
		userValidationBean.setUserName(userName);
		userValidationBean.setSkipValidationType(skipValidationType);
		
		for (UserValidator userValidator : userValidatorList) {
			if (!errors.hasErrors()) {
				ValidationUtils.invokeValidator(userValidator, userValidationBean, exceptions);
			}
		}
		// Example - ends here
		
		
		System.out.println("inside save user");
		System.out.println(ServletRequestUtils.getRequiredStringParameter(request,"oper"));
		System.out.println(ServletRequestUtils.getRequiredStringParameter(request,"uname"));
		ValidatorAdapter validatorAdapter = new ValidatorAdapter(modSubValidator, modelMap, request);
		validatorAdapter.validate(modsub, errors);
		if (errors.hasErrors()) {
			return loginView;
		}
		try
		{
			boolean result = subService.Submission(modsub);
			if(BooleanUtils.isTrue(result))
			{
				ControllerUtils.saveMessage(request, "information is saved");
				return loginView;
			}
			else
			{
				return "fail";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "fail";
		}
	}
	@RequestMapping(method = RequestMethod.POST, params={EDITUSER})
	public void editUser(@ModelAttribute("submitform")ModelSub modsub,Model model, HttpServletRequest request)throws Exception
	{
		System.out.println("inside edit user");
		System.out.println(ServletRequestUtils.getRequiredStringParameter(request,"oper"));
		System.out.println(ServletRequestUtils.getRequiredStringParameter(request,"uname"));
	}
	@RequestMapping(method = RequestMethod.POST, params={DELUSER})
	public String deleteUser(@ModelAttribute("submitform")ModelSub modsub,Model model, HttpServletRequest request)throws Exception
	{
		System.out.println("inside delet user");
		return REDIRECT_LOGIN_VIEW;
	}
}
