package org.springframework.learn.factory;

import org.springframework.learn.bean.UserModel;

/**
 * 静态工厂用于创建bean
 * @author zhangcy
 */
public class StaticFactory {

	/**
	 * 直接创建用户模型对象
	 */
	public static UserModel buildSimpleUser() {
		return new UserModel();
	}

	/**
	 * 创建复杂的用户模型对象
	 */
	public static UserModel buildComplexUser(String name, int age) {
		return new UserModel(name, age);
	}
}
