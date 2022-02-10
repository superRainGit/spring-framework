package org.springframework.learn.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.learn.interfaces.ILearnSpringInterface;

/**
 * @author zhangcy
 */
public class LearnSpringImpl implements ILearnSpringInterface, ApplicationContextAware, InitializingBean {

	private String age;

	private ApplicationContext applicationContext;

	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * 说一个hi 返回说的结果
	 *
	 * @param name 名字
	 * @return 返回成功与否
	 */
	@Override
	public String sayHello(String name) {
		System.out.println("hi " + name + " and age " + age);
		return "success";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.age = "200";
	}
}
