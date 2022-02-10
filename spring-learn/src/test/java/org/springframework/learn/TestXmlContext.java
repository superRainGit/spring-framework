package org.springframework.learn;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.learn.interfaces.ILearnSpringInterface;

public class TestXmlContext {

	@Test
	public void testSpecifiedXml() {
		// 文件绝对逻辑
		String filePath = "classpath:spring-config.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
		ILearnSpringInterface learnSpring = (ILearnSpringInterface) context.getBean("learnSpring");
		learnSpring.sayHello("Tom");
	}


}
