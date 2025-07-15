package org.springframework.learn.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.learn.interfaces.ILearnSpringInterface;

public class TestLearnSpringAutowired {

	@Autowired
	private ILearnSpringInterface learnSpringInterface;

	public void testLearnSpringAutowired(String name) {
		System.out.println(learnSpringInterface.sayHello(name));
	}
}
