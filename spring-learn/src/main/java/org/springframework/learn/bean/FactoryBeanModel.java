package org.springframework.learn.bean;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanModel implements FactoryBean<FactoryBeanModel.FactoryBeanModelInner> {

	public static class FactoryBeanModelInner {

		public void test() {
			System.out.println("I am factory bean model inner");
		}
	}

	private static final FactoryBeanModelInner bean = new FactoryBeanModelInner();

	@Override
	public FactoryBeanModelInner getObject() {
		return bean;
	}

	@Override
	public Class<?> getObjectType() {
		return FactoryBeanModelInner.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
