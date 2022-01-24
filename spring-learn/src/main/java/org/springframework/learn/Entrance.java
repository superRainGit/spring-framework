package org.springframework.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.learn.interfaces.ILearnSpringInterface;

/**
 * 入口函数
 * @author zhangcy
 */
public class Entrance {

	public static void main(String[] args) {
		// 文件绝对逻辑
		String filePath = "classpath:spring-config.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
		ILearnSpringInterface learnSpring = (ILearnSpringInterface) context.getBean("learnSpring");
		learnSpring.sayHello("Tom");
	}
}
