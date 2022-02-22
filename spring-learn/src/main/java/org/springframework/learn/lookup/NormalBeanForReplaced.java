package org.springframework.learn.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 测试replaced-method
 * @author zhangcy
 */
public class NormalBeanForReplaced {

	public static class ServiceA {

	}

	public static class ServiceB {

		private ServiceA serviceA;

		public ServiceA getServiceA() {
			return serviceA;
		}

		@Override
		public String toString() {
			return "ServiceB{" +
					getClass().getSimpleName() +
					"serviceA=" + getServiceA() +
					'}';
		}
	}

	public static class ServiceMethodReplacer implements MethodReplacer, ApplicationContextAware {

		private ApplicationContext context;

		/**
		 * Reimplement the given method.
		 *
		 * @param obj    the instance we're reimplementing the method for
		 * @param method the method to reimplement
		 * @param args   arguments to the method
		 * @return return value for the method
		 */
		@Override
		public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
			System.out.println("proxy method name: " + method.getName());
			System.out.println("args: " + Arrays.toString(args));
			return this.context.getBean(ServiceA.class);
		}

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.context = applicationContext;
		}
	}
}
