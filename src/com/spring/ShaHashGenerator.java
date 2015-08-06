package com.spring;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class ShaHashGenerator {

	public static void main(String[] args) {
			String password = "Password1";
		    ShaPasswordEncoder sha = new ShaPasswordEncoder();
		    String hash = sha.encodePassword(password, null);
		    System.out.println("sha hash password without salt===> "+hash);//70ccd9007338d6d81dd3b6271621b9cf9a97ea00
		    
		    String saltHash = sha.encodePassword(password, 9991);
		    System.out.println("sha hash password with salt 9991===> "+saltHash);//86475810ae8165b8dd6226b89c7d402ef6ed23e0
		    
		    String saltHash1 = sha.encodePassword(password, 9992);
		    System.out.println("sha hash password with salt 9992===> "+saltHash1);//270d014687333239a43b89c816bd2d3e48bed097
		    
		    StandardPasswordEncoder encoder = new StandardPasswordEncoder();
	        String result = encoder.encode(password);
	        System.out.println("standard ==> "+result);
	        
	        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String hashedPassword = passwordEncoder.encode(password);
	        System.out.println("bcrypt ===>"+hashedPassword);
	}
	

}
