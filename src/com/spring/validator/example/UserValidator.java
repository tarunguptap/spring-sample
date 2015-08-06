package com.spring.validator.example;

import org.springframework.validation.Validator;

import com.spring.enums.example.UserValidationType;


public interface UserValidator extends Validator {

    public UserValidationType getValidatorType();

}
