package org.springframework.learn.depend;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 正常的bean
 * 没有任何的依赖关系
 * @author zhangcy
 */
public class NormalBean {

	public static class Bean1 implements DisposableBean {

		public Bean1() {
			System.out.println("bean1 created");
		}

		@Override
		public void destroy() throws Exception {
			System.out.println("bean1 destroy()");
		}
	}

	public static class Bean2 implements DisposableBean {

		public Bean2() {
			System.out.println("bean2 created");
		}

		@Override
		public void destroy() throws Exception {
			System.out.println("bean2 destroy()");
		}
	}


	public static class Bean3 implements DisposableBean {

		public Bean3() {
			System.out.println("bean3 created");
		}

		@Override
		public void destroy() throws Exception {
			System.out.println("bean3 destroy()");
		}
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext() {

			@Override
			protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
				super.postProcessBeanFactory(beanFactory);
			}
		};
	}
}
