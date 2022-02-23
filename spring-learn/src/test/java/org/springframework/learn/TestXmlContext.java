package org.springframework.learn;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cglib.proxy.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.learn.autowire.DiByConstructor;
import org.springframework.learn.autowire.DiByType;
import org.springframework.learn.autowire.DiByTypeExtend;
import org.springframework.learn.interfaces.ILearnSpringInterface;
import org.springframework.learn.lookup.NormalBeanForLookUp;
import org.springframework.learn.lookup.NormalBeanForReplaced;
import org.springframework.learn.proxy.IService;
import org.springframework.learn.proxy.MethodCostHandler;
import org.springframework.learn.proxy.ServiceA;
import org.springframework.learn.scope.BeanScopeModel;
import org.springframework.learn.scope.ThreadScope;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
		String filePath = "classpath:lookup-config.xml";
		this.context = new ClassPathXmlApplicationContext(filePath);
		logger.debug("spring容器启动完毕！");
	}

	/**
	 * 设置多callBack的场景
	 */
	@Test
	public void testMultipleCallBack() {
		Enhancer enhancer = new Enhancer();
		// 设置父类
		enhancer.setSuperclass(ServiceA.class);
		// 设置两个callBack
		// 这个是拦截方法调用
		MethodInterceptor methodInterceptor = (obj, method, args, methodProxy) -> {
			logger.debug("invoke method " + method.getName());
			long start = System.nanoTime();
			Object result = methodProxy.invokeSuper(obj, args);
			long end = System.nanoTime();
			logger.debug("invoke cost time " + (end - start));
			return result;
		};
		// 这个是返回默认值
		FixedValue fixedValue = () -> "default value";
		// 设置回调
		enhancer.setCallbacks(new Callback[]{methodInterceptor, fixedValue});
		// 设置一个callBack的过滤器
		enhancer.setCallbackFilter((method) -> {
			String name = method.getName();
			if(name.startsWith("get")) {
				return 1;
			}
			return 0;
		});
		ServiceA serviceA = (ServiceA) enhancer.create();
		serviceA.m1();
		System.out.println(serviceA.get1());
	}

	@Test
	public void testCglib() {
		// 创建Enhancer对象
		Enhancer enhancer = new Enhancer();
		// 设置父类
		enhancer.setSuperclass(ServiceA.class);
		// 设置回调
		enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
			logger.debug("invoke parent before");
			Object result = methodProxy.invokeSuper(o, objects);
			logger.debug("invoke parent after");
			return result;
		});
		// 返回默认值
		enhancer.setCallback((FixedValue) () -> "return some value");
		// 设置直接放行 不做任何的拦截
		enhancer.setCallback(NoOp.INSTANCE);
		ServiceA serviceA = (ServiceA) enhancer.create();
		serviceA.m1();
		System.out.println(serviceA.toString());
		// serviceA.m2();
	}

	@Test
	public void testMethodCostHandler() {
		ServiceA serviceA = new ServiceA();
		IService proxy = MethodCostHandler.getProxy(serviceA, IService.class);
		proxy.m1();
		proxy.m2();
	}

	/**
	 * 测试简单代理
	 */
	@Test
	public void testSimpleProxy() throws Exception {
		// 这种方法已经废弃了
		Class<IService> proxyClass = (Class<IService>) Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
		InvocationHandler handler = (o, method, objects) -> {
			System.out.println("invoke method " + method.getName());
			return null;
		};
		IService iService = proxyClass.getConstructor(InvocationHandler.class).newInstance(handler);
		iService.m1();
		iService.m2();
		// 使用更加简单的方式创建proxy对象
		iService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
				logger.debug("simple proxy invoke method " + method.getName());
				return null;
			}
		});
		iService.m1();
		iService.m2();
	}

	/**
	 * 测试replace-method
	 */
	@Test
	public void testReplaceMethod() {
		logger.debug("" + this.context.getBean(NormalBeanForReplaced.ServiceA.class));
		logger.debug("" + this.context.getBean(NormalBeanForReplaced.ServiceA.class));
		logger.debug("" + this.context.getBean(NormalBeanForReplaced.ServiceB.class));
		logger.debug("" + this.context.getBean(NormalBeanForReplaced.ServiceB.class));
	}

	/**
	 * 测试lookUp
	 */
	@Test
	public void testLookUp() {
		logger.debug("" + this.context.getBean(NormalBeanForLookUp.ServiceA.class));
		logger.debug("" + this.context.getBean(NormalBeanForLookUp.ServiceA.class));
		logger.debug("" + this.context.getBean(NormalBeanForLookUp.ServiceB.class));
		logger.debug("" + this.context.getBean(NormalBeanForLookUp.ServiceB.class));
	}

	/**
	 * 测试depends on 以及bean的初始化关系
	 */
	@Test
	public void testDependOn() {
		((ClassPathXmlApplicationContext)this.context).close();
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
