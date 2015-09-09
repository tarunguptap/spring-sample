package com.spring.rest.controller.v1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.domain.User;
import com.spring.error.CustomError;
import com.spring.exception.ValidationException;
import com.spring.rest.dto.UserDetailDTO;
import com.spring.service.UserService;

@Controller
@RequestMapping("/api/rest/v1/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	private MessageSourceAccessor messageSourceAccessor;
	
	@Resource
    public void setMessageSource(MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public UserDetailDTO getInvoiceQuote(@PathVariable Long userId) {
		User loggedInUser = userService.findUserById(userId);
		return populateUserDetailDTO(loggedInUser);
	}
	
	@ExceptionHandler(ValidationException.class)
    public @ResponseBody CustomError handleValidationException(ValidationException argumentException,
          HttpServletResponse httpResponse) {
	  logger.error(messageSourceAccessor.getMessage("error.validationException"), argumentException);
      CustomError error = new CustomError(argumentException.getCode(), argumentException.getMessage());
      httpResponse.setStatus(HttpServletResponse.SC_OK);
      return error;
    }
	
	 @ExceptionHandler(Exception.class)
	  public @ResponseBody CustomError handleRuntimeException(Exception exception, HttpServletResponse httpResponse) {
	      logger.error(messageSourceAccessor.getMessage("error.processing"), exception);
	      CustomError error = new CustomError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
	              messageSourceAccessor.getMessage("error.internal.error"));
	      httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	      return error;
	  }
	
	private UserDetailDTO populateUserDetailDTO(User user) {
		UserDetailDTO userDetail = new UserDetailDTO(user.getId(), user.getUsername(), user.getPassword(), user.getActive(), user.getDeleted(), user.getRoles());
		return userDetail;
	}
	
	
}
