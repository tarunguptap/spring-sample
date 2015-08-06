package com.spring.batch.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 
 * @version $Id$
 */
public class JobContextHelper {
    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("resources/batchContext.xml");
        }
        return context;
    }
    public static void main(String[] args) {
    	if(getApplicationContext()== null) {
    		System.out.println("context not loaded");
    	} else {
    		System.out.println("context is loaded");
    	}
    	
	}
    
}
