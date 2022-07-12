package org.springframework.learn.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * SaxParser工具类
 * @author zhangcy
 */
public class SaxParserUtil {

	public static String parseFile(String filePath) throws IOException, ParserConfigurationException, SAXException {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document root = documentBuilder.parse(inputStream);
		// 获取内部的beans标签
		NodeList childNodes = root.getChildNodes();
		int length = childNodes.getLength();
		for(int i = 0; i < length; i++) {
			// 获取beans标签
			Node beansItem = childNodes.item(i);
			NodeList beanNodes = beansItem.getChildNodes();
			for(int beanI = 0; beanI < beanNodes.getLength(); beanI++) {
				Node beansChild = beanNodes.item(beanI);
				if(beansChild instanceof Element) {
					if("bean".equals(beansChild.getNodeName())) {
						Element ele = (Element) beansChild;
						System.out.println(ele.getAttribute("id"));
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		parseFile("spring-book-config.xml");
	}
}
