package org.springframework.learn.type;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 测试获取类型边上上的泛型变量
 * @author zhangcy
 */
public class TypeDemo1<T1, T2 extends Integer, T3 extends Demo11 & Demo12> {

	/**
	 * 获取对应的类型上的泛型类型
	 */
	public static void main(String[] args) {
		// 获取类上的泛型化参数
		TypeVariable<Class<TypeDemo1>>[] typeParameters = TypeDemo1.class.getTypeParameters();
		for (TypeVariable<Class<TypeDemo1>> typeParameter : typeParameters) {
			// 变量的名称
			System.out.println("变量的名称: " + typeParameter.getName());
			// 变量定义所在类型
			System.out.println("变量是谁声明的: " + typeParameter.getGenericDeclaration());
			// 获取泛型类型的上界
			Type[] bounds = typeParameter.getBounds();
			System.out.println("泛型类型上界数量: " + bounds.length);
			System.out.println("这个变量上边界清单: ");
			// 泛型变量上界清单
			for (Type bound : bounds) {
				System.out.println(bound.getTypeName());
			}
			System.out.println("-----------------------");
		}
	}
}

interface Demo11 {}

interface Demo12 {}

