package org.springframework.learn.interfaces;

/**
 * @author zhangcy
 */
public interface ILearnSpringInterface {

	/**
	 * 说一个hi 返回说的结果
	 * @param name 名字
	 * @return 返回成功与否
	 */
	String sayHello(String name);

	default public void testLeanAutowired(String name) {}
}
