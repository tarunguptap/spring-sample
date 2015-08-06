 
package com.spring.validator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.MethodInvoker;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

 
public class ValidatorAdapter implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(ValidatorAdapter.class);
	private Object delegate;
	private HttpServletRequest httpServletRequest;
	private ModelMap modelMap;
	private String defaultValidatorMethod = "validate";

	public ValidatorAdapter(Object delegate, ModelMap map, HttpServletRequest request) {
		this.delegate = delegate;
		this.modelMap = map;
		this.httpServletRequest = request;
	}

	public void setDefaultValidatorMethod(String defaultValidatorMethod) {
		this.defaultValidatorMethod = defaultValidatorMethod;
	}

	@Override
	public boolean supports(Class<?> paramClass) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Object[] arguments = { target, errors, modelMap, httpServletRequest };
		MethodInvoker methodInvoker = new MethodInvoker();
		methodInvoker.setTargetObject(delegate);
		methodInvoker.setTargetMethod(defaultValidatorMethod);
		methodInvoker.setArguments(arguments);
		try {
			methodInvoker.prepare();
			methodInvoker.invoke();
		} catch (Exception e) {
			logger.error("Exception while validating ", e);
			throw new RuntimeException(e);
		}

	}

}
