package org.springframework.learn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用注解6
 * @author zhangcy
 */
@Target({ElementType.TYPE,
		ElementType.METHOD,
		ElementType.PARAMETER,
		ElementType.FIELD,
		ElementType.CONSTRUCTOR,
		ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@interface Ann6 {

	/**
	 * 值
	 */
	String value();

	/**
	 * 注入的地方和类型
	 */
	ElementType type();
}
/**
 * 测试使用注解
 * @author zhangcy
 */
@Ann6(value = "放置在类型上", type = ElementType.TYPE)
public class UseAnnotation6 {

	@Ann6(value = "放在属性上", type = ElementType.FIELD)
	private String a;

	@Ann6(value = "放在构造器上", type = ElementType.CONSTRUCTOR)
	public UseAnnotation6(@Ann6(value = "方法参数上", type = ElementType.PARAMETER) String a) {
		this.a = a;
	}

	@Ann6(value = "普通方法上", type = ElementType.METHOD)
	public void m2() {

		@Ann6(value = "普通变量上", type = ElementType.LOCAL_VARIABLE)
		int m = 10;
	}
}
