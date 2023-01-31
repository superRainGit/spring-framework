package org.springframework.learn.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.learn.interfaces.ILearnSpringInterface;

/**
 * @author zhangcy
 */
public class LearnSpringImpl implements ILearnSpringInterface, ApplicationContextAware, InitializingBean, EnvironmentAware {

	private String age;

	private ApplicationContext applicationContext;

	private Environment environment;

	public void setAge(String age) {
		this.age = age;
		System.out.println("LearnSpringImpl setAge --> " + this);
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
		this.age = environment.getProperty("name");
		System.out.println("LearnSpringImpl afterPropertiesSet --> " + this);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "LearnSpringImpl{" +
				"age='" + age + '\'' +
				'}';
	}
}
