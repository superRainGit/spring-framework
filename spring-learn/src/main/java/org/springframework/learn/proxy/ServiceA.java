package org.springframework.learn.proxy;

/**
 * serviceA
 * @author zhangcy
 */
public class ServiceA implements IService {

	/**
	 * 方法测试m1
	 */
	@Override
	public void m1() {
		System.out.println("invoke m1");
	}

	/**
	 * 方法测试m2
	 */
	@Override
	public void m2() {
		System.out.println("invoke m2");
	}

	/**
	 * 测试获取
	 */
	public String get1() {
		return "service A get";
	}
}
