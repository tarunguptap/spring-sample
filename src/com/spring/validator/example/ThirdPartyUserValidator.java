package com.spring.validator.example;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.spring.bean.example.UserValidationBean;
import com.spring.enums.example.UserValidationType;

@Component("thirdPartyUserValidator")
public class ThirdPartyUserValidator implements UserValidator {

    @Override
    public UserValidationType getValidatorType() {
        return UserValidationType.THIRDPARTYVAL;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserValidationBean.class);
    }

    @Override
    public void validate(Object imeiValidationObject, Errors errors) {
    	UserValidationBean userValidationBean = (UserValidationBean) imeiValidationObject;
        if (userValidationBean != null && !(userValidationBean.getSkipValidationType().contains(getValidatorType()))) {
        	if(userValidationBean.getUserName() == null) {
            	errors.reject("error.user.not.found");
            }
        }
    }

}
