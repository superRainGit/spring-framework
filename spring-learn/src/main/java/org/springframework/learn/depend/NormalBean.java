package org.springframework.learn.depend;

import org.springframework.beans.factory.DisposableBean;

/**
 * 正常的bean
 * 没有任何的依赖关系
 * @author zhangcy
 */
public class NormalBean {

	public static class Bean1 implements DisposableBean {

		@Override
		public void destroy() throws Exception {
			System.out.println("bean1 destroy()");
		}
	}

}
