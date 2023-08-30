package org.springframework.learn.impl;

/**
 * 使用构造器的方式去初始化
 * @author zhangcy
 */
public class LearnSpringImplConstructorChild extends LearnSpringImplConstructor {

	public LearnSpringImplConstructorChild(String name) {
		super(name);
	}

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}
}
