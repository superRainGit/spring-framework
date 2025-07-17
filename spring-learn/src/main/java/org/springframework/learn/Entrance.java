package org.springframework.learn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.learn.impl.LearnSpringImplConstructor;
import org.springframework.learn.impl.TestLearnSpringAutowired;
import org.springframework.learn.interfaces.ILearnSpringInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * 入口函数
 * @see http://itsoku.com/course/5/86
 * @author zhangcy
 */
public class Entrance {

	/**
	 * 使用file读取文件的路径
	 * 这种的一般都是绝对路径
	 * file("/")
	 * 这种属于是相对路径 相对于JVM启动的路径
	 * file("./")
	 *
	 * 建议使用classLoader去读取
	 * 默认的AppClassLoader读取classPath中的文件
	 * 如果是自定义的classLoader则在指定的目录下查找
	 *
	 * 带上斜线 因为class里面回去摘掉对应的/
	 * 如果没有的话 则直接会以当前的class所在的路径进行处理
	 * 然后使用class.getResourceAsStream("/")
	 * 不要带斜线
	 * classLoader.getResourceAsStream("")
	 *
	 * 总的理解来说  尽量使用不以 / 开头去读取文件
	 * 因为会将 / 解释为跟目录去读取  除非使用class.getResourceAsStream()的方式去读取
	 */


	private static Log logger = LogFactory.getLog(Entrance.class);

	public static void main(String[] args) throws Exception {
		// system environment System.getEnv() 这个获取的是操作系统的变量
		// System.out.println(System.getenv().get("PATH"));
		// system properties System.getProperties() 这个获取的是java 设置到 JVM 中的参数
		// System.out.println(System.getProperty("def"));
		// ApplicationContext context = new ClassPathXmlApplicationContext("spring-simple.xml");
		// ILearnSpringInterface bean = context.getBean(ILearnSpringInterface.class);
		// bean.sayHello("Tom");
//		System.out.println(Object.class.isAssignableFrom(Integer.class));
//		System.out.println(Object.class.isAssignableFrom(int.class));
//		System.out.println(Object.class.isAssignableFrom(List.class));
//		System.out.println(Collection.class.isAssignableFrom(List.class));
//		System.out.println(List.class.isAssignableFrom(List.class));
//		System.out.println(List.class.isAssignableFrom(Collection.class));
		// System.setProperty("spring", "spring");
		// String filePath = "classpath:${spring:abc}-config.xml";
		// ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
		// ILearnSpringInterface learnSpring = (ILearnSpringInterface) context.getBean("learnSpring");
		// learnSpring.sayHello("Tom");
		// getAllBeans(context);
		// BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring-book-config.xml"));
		// LearnSpringImpl learnSpring = bf.getBean("learnSpringChild", LearnSpringImpl.class);
		// learnSpring.sayHello("Tom");
		// NormalBeanForLookUp.ServiceB serviceB = bf.getBean("serviceB", NormalBeanForLookUp.ServiceB.class);
		// System.out.println(serviceB);
		// AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
		// ILearnSpringInterface bean = context.getBean(ILearnSpringInterface.class);
		// System.out.println(bean);
		// MergedAnnotations from = MergedAnnotations.from(ConfigurationTest.class,
		// 		MergedAnnotations.SearchStrategy.DIRECT, RepeatableContainers.none(),
		// 		AnnotationFilter.NONE);
		// System.out.println(from);
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationTest.class);
		ILearnSpringInterface bean = context.getBean(ILearnSpringInterface.class);
		System.out.println(bean.sayHello("Tom"));
		bean.testLeanAutowired("YAMAHA");
		TestLearnSpringAutowired test = context.getBean(TestLearnSpringAutowired.class);
		test.testLearnSpringAutowired("ABC");
	}

	@Configuration
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public static class ConfigurationTest {

		@Bean
		public TestLearnSpringAutowired testLearnSpringAutowired() {
			return new TestLearnSpringAutowired();
		}

		@Bean
		public ILearnSpringInterface learnSpring() {
			return new LearnSpringImplConstructor("test");
		}

	}

	public static void getAllBeans(ApplicationContext context) {
		for (String beanDefinitionName : context.getBeanDefinitionNames()) {
			logger.debug("bean value is " + context.getBean(beanDefinitionName));
		}
	}

	public static void getBeanAndAlias(ApplicationContext context) {
		// XML文件里面配置的bean标签 bean的id和name属性的区分
		// 如果配置了id和name 那么id是bean的名字 name是别名
		// 如果没有配置id 那么name里面的第一个能解析出来的是名字 其他的是别名
		// 如果均没有配置 那么classType#序列号[从0开始]是名字  classType是第一个同类型bean名字的别名 其他的没有别名
		// 获取所有bean的定义信息
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			String[] aliases = context.getAliases(beanDefinitionName);
			logger.debug("get bean name: " + beanDefinitionName + " and alias: " + String.join(",", aliases));
		}
	}

	/**
	 * 通过DOM方式解析对应的xml文档
	 * 将整个的xml文档解析到内存中
	 */
	@SuppressWarnings("unused")
	public static void parseXmlByDom() throws Exception {
		// 创建documentBuilderFactory对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		// 将路径下的XML文档加载在内存里面
		Document document = documentBuilder.parse("spring-learn/src/main/resources/spring-config.xml");
		Element documentElement = document.getDocumentElement();
		NodeList childNodes = document.getChildNodes();
		for(int i = 0; i < childNodes.getLength(); i++) {
			System.out.println(childNodes.item(i).getNodeName());
		}
	}

	/**
	 * 使用classLoader获取类路径下的文件
	 */
	@SuppressWarnings("unused")
	public static void readFileByClassLoader() throws Exception {
		// ClassLoader classLoader = Entrance.class.getClassLoader();
		ClassLoader classLoader = new MyClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("logback.xml");
		if(resourceAsStream != null) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
			String s;
			while((s = bufferedReader.readLine()) != null) {
				System.out.println(s);
			}
			bufferedReader.close();
		} else {
			throw new NullPointerException();
		}
	}

	public static class MyClassLoader extends ClassLoader {

	}

	/**
	 * 使用文件读取相对项目跟目录中的文件
	 */
	@SuppressWarnings("unused")
	public static void readFileByFile() throws Exception {
		// 使用File对象读取文件
		// 默认的当前路径是当前项目的目录为根目录
		File file = new File("spring-learn/src/main/resources/spring-config.xml");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String s;
		while((s = bufferedReader.readLine()) != null) {
			System.out.println(s);
		}
		bufferedReader.close();
	}
}
