package org.springframework.learn.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author zhangcy
 */
public class SelfBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("invoke self bean factory post processor");
		BeanDefinition learnSpring = beanFactory.getBeanDefinition("learnSpring");
		MutablePropertyValues propertyValues = learnSpring.getPropertyValues();
		System.out.println("learn spring bean invoke change properties before " + propertyValues.toString());
		if(propertyValues.contains("age")) {
			propertyValues.add("age", "999999");
		}
		System.out.println("learn spring bean invoke change properties after " + propertyValues.toString());
	}
}
