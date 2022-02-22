package org.springframework.learn.lookup;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zhangcy
 */
public class NormalBeanForLookUp {

	public static class ServiceA {

	}

	/**
	 * 因为serviceB这个里面注入的时候直接创建的A 没有办法直接在每次获取ServiceB的实例的时候去创建ServiceA的方式
	 * 可以直接用实现applicationContextAware的接口去使用applicationContext
	 */
	public static class ServiceB implements ApplicationContextAware {

		private ServiceA serviceA;

		private ApplicationContext applicationContext;

		public ServiceA getServiceA() {
			return null;
		}

		@Override
		public String toString() {
			return "ServiceB{" +
					"serviceA=" + getServiceA() +
					'}';
		}

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext;
		}
	}

}
