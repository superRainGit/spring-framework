package org.springframework.learn.autowire;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.learn.impl.LearnSpringImplConstructor;
import org.springframework.learn.impl.LearnSpringImplConstructorChild;

import java.util.List;
import java.util.Map;

/**
 * @author zhangcy
 */
public class DiByTypeNew {

	private List<LearnSpringImplConstructor> list;

	public void setList(List<LearnSpringImplConstructor> list) {
		this.list = list;
	}

	private Map<String, LearnSpringImplConstructorChild> map;

	public void setMap(Map<String, LearnSpringImplConstructorChild> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "DiByTypeNew{" +
				"list=" + list +
				", map=" + map +
				'}';
	}

	public static void main(String[] args) {
		String file = "spring-autowire.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(file);
		DiByTypeNew bean = context.getBean(DiByTypeNew.class);
		System.out.println(bean);
		context.close();
	}
}