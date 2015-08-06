package com.spring.bean.example;

import java.util.ArrayList;
import java.util.List;

import com.spring.enums.example.UserValidationType;

public class UserValidationBean {
	
	private List<UserValidationType> skipValidationType = new ArrayList<UserValidationType>();
	private String userName;

	public List<UserValidationType> getSkipValidationType() {
		return skipValidationType;
	}

	public void setSkipValidationType(List<UserValidationType> skipValidationType) {
		this.skipValidationType = skipValidationType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
