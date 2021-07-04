package com.dvdrental.inventory.common;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DBFunctions {
	public Object getDaoImplBean(String daoImplBeanName,ServletContext servletContext) {
		ApplicationContext applicationContext=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		Object object=	applicationContext.getBean(daoImplBeanName);
		return object;
	}

}
