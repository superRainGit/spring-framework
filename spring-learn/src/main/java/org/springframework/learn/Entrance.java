package org.springframework.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.learn.interfaces.ILearnSpringInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * 入口函数
 * @author zhangcy
 */
public class Entrance {

	public static void main(String[] args) throws Exception {
		System.setProperty("spring", "spring");
		String filePath = "classpath:${spring:abc}-config.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(filePath);
		ILearnSpringInterface learnSpring = (ILearnSpringInterface) context.getBean("learnSpring");
		learnSpring.sayHello("Tom");
//		parseXmlByDom();
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
