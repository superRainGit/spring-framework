package org.springframework.learn.bean;

/**
 * 用户模块对象
 * @author zhangcy
 */
public class UserModel {

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 年龄
	 */
	private int age;

	public UserModel() {
		this.name = "测试名称";
	}

	public UserModel(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
