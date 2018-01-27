package com.alycloud.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Moyq5
 * @date 2017年10月17日
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}
	
	public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
     
    public static Object getBean(String id){
        return applicationContext.getBean(id);
    }
     
    public static <T> T getBean(Class<T> c){
        return applicationContext.getBean(c);
    }
     
}
