package org.springframework.learn.impl;

import org.springframework.learn.interfaces.ILearnSpringInterface;

/**
 * 使用构造器的方式去初始化
 * @author zhangcy
 */
public class LearnSpringImplConstructor implements ILearnSpringInterface {

	private String name;

	public LearnSpringImplConstructor(String name) {
		this.name = name;
	}

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

	@Override
	public String toString() {
		return "LearnSpringImplConstructor{" +
				"name='" + name + '\'' +
				'}';
	}
}
