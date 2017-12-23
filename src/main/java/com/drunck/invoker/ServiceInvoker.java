package com.drunck.invoker;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ServiceInvoker implements ApplicationContextAware {
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ServiceInvoker.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static Object invoke(String name) {
		return getContext().getBean(name);
	}

	public static <T> T invoke(Class<T> clazz) {
		return getContext().getBean(clazz);
	}
}
