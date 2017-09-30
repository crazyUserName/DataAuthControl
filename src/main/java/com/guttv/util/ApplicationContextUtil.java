package com.guttv.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	public static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		ApplicationContextUtil.context = ctx;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}
}
