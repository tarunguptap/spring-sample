 
package com.spring.validator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;


/**
 * @author tgupta
 * 
 */
@Component
public class ModSubValidator {
	
	private MessageSourceAccessor messageSourceAccessor;

	@Resource
	public void setMessageSource(MessageSource messageSource) {
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}

	public void validate(Object target, Errors errors, ModelMap modelMap, HttpServletRequest request) {
		try {
			String userid = (String)ServletRequestUtils.getRequiredStringParameter(request,"oper");
			if(StringUtils.isBlank(userid)) {
				errors.reject("error.invalid.modsubid");
			}
		} catch (ServletRequestBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
