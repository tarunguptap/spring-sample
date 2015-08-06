package com.spring.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class SpringContextHelper {

    private static ThreadLocal<SessionFactory> threadLocalsessionFactory = new ThreadLocal<SessionFactory>();

	public static ApplicationContext getApplicationContext() {
		String[] configLocations = { "/applicationContext.xml", "/daoContext.xml", "/securityContext.xml",
				"/serviceContext.xml", "/validatorContext.xml", "/jdbcDatasourceContext.xml", "/workflowContext.xml" };
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		return applicationContext;
	}

	public static ApplicationContext getContextWithMiddleware() {
		String[] configLocations = { "/applicationContext.xml", "/rabbitmq-config.xml", "/daoContext.xml",
				"/securityContext.xml", "/serviceContext.xml", "/validatorContext.xml", "/jdbcDatasourceContext.xml",
				"/workflowContext.xml" };
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		return applicationContext;
	}

    public static Session getHibernateSession(ApplicationContext context) {
        SessionFactory sessionFactory = (SessionFactory) context
                .getBean("mySessionFactory");
        threadLocalsessionFactory.set(sessionFactory);
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        TransactionSynchronizationManager.bindResource(sessionFactory,
                new SessionHolder(session));
        return session;
    }

    public static void closeHibernateSession() {
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager
                .unbindResource(threadLocalsessionFactory.get());
        SessionFactoryUtils.releaseSession(sessionHolder.getSession(),
                threadLocalsessionFactory.get());
        threadLocalsessionFactory.set(null);
        // threadLocalsessionFactory = null;
    }
    
    public static ApplicationContext getApplicationContextForTestNg() {
		String[] configLocations = { "/resources/applicationContext.xml" };
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		return applicationContext;
	}
}
