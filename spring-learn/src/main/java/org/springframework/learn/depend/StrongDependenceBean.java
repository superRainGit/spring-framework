package org.springframework.learn.depend;

import org.springframework.beans.factory.DisposableBean;

/**
 * 正常的bean
 * 没有任何的依赖关系
 * @author zhangcy
 */
public class StrongDependenceBean {

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

		private Bean1 bean1;

		public Bean2(Bean1 bean1) {
			this.bean1 = bean1;
			System.out.println("bean2 created");
		}

		@Override
		public void destroy() throws Exception {
			System.out.println("bean2 destroy()");
		}
	}


	public static class Bean3 implements DisposableBean {

		private Bean2 bean2;

		public Bean3(Bean2 bean2) {
			this.bean2 = bean2;
			System.out.println("bean3 created");
		}

		@Override
		public void destroy() throws Exception {
			System.out.println("bean3 destroy()");
		}
	}
}
