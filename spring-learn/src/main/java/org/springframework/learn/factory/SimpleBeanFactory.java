package org.springframework.learn.factory;

import org.springframework.learn.bean.UserModel;

/**
 * 简单的bean工厂
 * @author zhangcy
 */
public class SimpleBeanFactory {

	/**
	 * 构建简单用户信息
	 */
	public UserModel buildSimpleUser() {
		return new UserModel();
	}

	/**
	 * 构建复杂用户信息
	 */
	public UserModel buildComplexUser(String name, int age) {
		return new UserModel(name, age);
	}
}
