package org.springframework.learn.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.learn.interfaces.ILearnSpringInterface;

/**
 * 使用构造器的方式去初始化
 * @author zhangcy
 */
public class LearnSpringImplConstructor implements ILearnSpringInterface {

	@Autowired
	private TestLearnSpringAutowired testLearnSpringAutowired;

	private String name;

	public LearnSpringImplConstructor(String name) {
		this.name = name;
	}

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

	public void testLeanAutowired(String name) {
		this.testLearnSpringAutowired.testLearnSpringAutowired(name);
	}

	@Override
	public String toString() {
		return "LearnSpringImplConstructor{" +
				"name='" + name + '\'' +
				'}';
	}
}
