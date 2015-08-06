package com.spring.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public abstract class ControllerUtils {
	private static final String YEAR_FOUR_DIGITS = "yyyy";
	private static final String YEAR_TWO_DIGITS = "yy";
	private static final String YEAR_SINGLE_DIGIT = "y";
	public static final void saveMessage(HttpServletRequest request,
			String message) {
		List<String> messages = (List<String>) request.getSession(false)
				.getAttribute(GeneralConstants.REQUEST_MESSAGE_KEY);
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		if (!messages.contains(message)) {
			messages.add(message);
		}
		request.getSession(false).setAttribute(GeneralConstants.REQUEST_MESSAGE_KEY,
				messages);
	}

	public static final void saveError(HttpServletRequest request,
			BindException errors) {
		request.getSession(false).setAttribute(GeneralConstants.REQUEST_ERROR_KEY,
				errors);
	}
	public static final void saveError(HttpServletRequest request,
			BindingResult errors) {
		request.getSession(false).setAttribute(GeneralConstants.REQUEST_ERROR_KEY,
				errors);
	}
	public static final void removeError(HttpServletRequest request){
		request.getSession(false).removeAttribute(GeneralConstants.REQUEST_ERROR_KEY);
	}
	public static final BindException getErrors(HttpServletRequest request){
		return	(BindException) request.getSession(false).getAttribute(GeneralConstants.REQUEST_ERROR_KEY);
	}
	public static String getUserLocaleDateRangePickerPattern(){
		String dateFormat = getUserLocaleDatePattern();
		if(dateFormat != null && !dateFormat.isEmpty()){
			if(dateFormat.contains(YEAR_FOUR_DIGITS)){
				dateFormat = dateFormat.replace(YEAR_FOUR_DIGITS, YEAR_TWO_DIGITS);
			}else if(dateFormat.contains(YEAR_TWO_DIGITS)){
				dateFormat = dateFormat.replace(YEAR_TWO_DIGITS, YEAR_SINGLE_DIGIT);
			}
		}
		return dateFormat;
	}
	public static FastDateFormat getUserLocaleDateFormat(){
		Locale locale = Locale.US;
		return  FastDateFormat.getDateInstance(
				FastDateFormat.SHORT, locale);
	}
	public static String getUserLocaleDatePattern(){
		
		return  getUserLocaleDateFormat().getPattern().toLowerCase();
	}
}
