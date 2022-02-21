package org.springframework.learn;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.learn.autowire.DiByConstructor;
import org.springframework.learn.autowire.DiByType;
import org.springframework.learn.autowire.DiByTypeExtend;
import org.springframework.learn.interfaces.ILearnSpringInterface;
import org.springframework.learn.scope.BeanScopeModel;
import org.springframework.learn.scope.ThreadScope;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestXmlContext {

	private ApplicationContext context;

	private static Logger logger;

	static {
		// logger = LogFactory.getLog(TestXmlContext.class);
		JoranConfigurator configurator = new JoranConfigurator();
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		configurator.setContext(lc);
		lc.reset();
		try {
			configurator.doConfigure("src/test/resources/logback.xml");
		} catch (JoranException e) {
			e.printStackTrace();
		}
		logger = lc.getLogger(Entrance.class);
//		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
	}

	@Before
	public void before() {
		logger.debug("spring容器准备启动.....");
		// 文件绝对逻辑
		String filePath = "classpath:autowire-config.xml";
		this.context = new ClassPathXmlApplicationContext(filePath);
		logger.debug("spring容器启动完毕！");
	}

	/**
	 * 按照类型自动装配扩展
	 */
	@Test
	public void testAutowireByConstructor() {
		logger.debug("获取对应的bean");
		logger.debug("bean: " + this.context.getBean(DiByConstructor.class));
	}

	/**
	 * 按照类型自动装配扩展
	 */
	@Test
	public void testAutowireByTypeExtend() {
		logger.debug("获取对应的bean");
		logger.debug("bean: " + this.context.getBean(DiByTypeExtend.class));
	}

	/**
	 * 按照类型自动装配
	 */
	@Test
	public void testAutowireByType() {
		logger.debug("获取对应的bean");
		logger.debug("bean: " + this.context.getBean(DiByType.class));
	}

	/**
	 * 按照名称自动装配
	 */
	@Test
	public void testAutowireByName() {
		logger.debug("获取对应的bean");
		logger.debug("bean: " + this.context.getBean("diByName1"));
		logger.debug("bean: " + this.context.getBean("diByName2"));
	}

	/**
	 * isAssignableFrom 这个是用来指定参数中的类型是不是可以被赋值给当前类型
	 */
	@Test
	public void testAssignableFrom() {
		System.out.println(Object.class.isAssignableFrom(Integer.class));
		System.out.println(Object.class.isAssignableFrom(int.class));
		System.out.println(Object.class.isAssignableFrom(List.class));
		System.out.println(Collection.class.isAssignableFrom(List.class));
		System.out.println(List.class.isAssignableFrom(List.class));
		System.out.println(List.class.isAssignableFrom(Collection.class));
	}

	/**
	 * 测试自定义bean
	 */
	@Test
	public void testCustomizeScope() throws InterruptedException {
		this.context = new ClassPathXmlApplicationContext() {
			@Override
			protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
				beanFactory.registerScope(ThreadScope.SCOPE_NAME, new ThreadScope());
				super.postProcessBeanFactory(beanFactory);
			}
		};
		String filePath = "classpath:scope-config.xml";
		((ClassPathXmlApplicationContext)this.context).setConfigLocation(filePath);
		((ClassPathXmlApplicationContext) this.context).refresh();
		logger.debug("获取对应的bean");
		new Thread(() -> {
			logger.debug("bean: " + this.context.getBean(BeanScopeModel.class));
			logger.debug("bean: " + this.context.getBean(BeanScopeModel.class));
		}).start();
		new Thread(() -> {
			logger.debug("bean: " + this.context.getBean(BeanScopeModel.class));
			logger.debug("bean: " + this.context.getBean(BeanScopeModel.class));
		}).start();
		TimeUnit.SECONDS.sleep(5);
	}

	@Test
	public void testSingletonBean() {
		logger.debug("获取对应的bean");
		logger.debug("bean: " + this.context.getBean(BeanScopeModel.class));
		logger.debug("bean: " + this.context.getBean(BeanScopeModel.class));
		logger.debug("bean: " + this.context.getBean(BeanScopeModel.class));
	}

	@Test
	public void testSpecifiedXml() {
		// 文件绝对逻辑
		String filePath = "classpath:spring-config.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
		ILearnSpringInterface learnSpring = (ILearnSpringInterface) context.getBean("learnSpring");
		learnSpring.sayHello("Tom");
	}


}
