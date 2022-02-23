package org.springframework.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 任意接口统计代理统计处理类
 * @author zhangcy
 */
public class MethodCostHandler<T> implements InvocationHandler {

	private T target;

	public MethodCostHandler(T target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
		long start = System.currentTimeMillis();
		Object invoke = method.invoke(target, objects);
		long end = System.currentTimeMillis();
		System.out.println("cost = " + (end - start));
		return invoke;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Object target, Class<T> targetInterface) {
		if(targetInterface == null || !targetInterface.isInterface()) {
			throw new IllegalArgumentException("target " + targetInterface + " must be interface");
		}
		if(!targetInterface.isAssignableFrom(target.getClass())) {
			throw new IllegalArgumentException("target " + target + " must be interface sub class");
		}
		return (T) Proxy.newProxyInstance(targetInterface.getClassLoader(),
				new Class[]{targetInterface}, new MethodCostHandler<>(target));
	}
}
