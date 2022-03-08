package org.springframework.learn.type;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * 泛型类型
 * @author zhangcy
 */
public class TypeDemo4<T> {

	public class C1 {

		public List<T> m1(List<T> list) {
			System.out.println(list.getClass());
			return list;
		}
	}

	public static void main(String[] args) throws NoSuchMethodException {
		Method method = TypeDemo4.C1.class.getMethod("m1", List.class);
		// 获取m1方法的参数类型
		Type parameterType = method.getGenericParameterTypes()[0];
		System.out.println("m1方法参数类型信息-----------------");
		if(parameterType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) parameterType;
			System.out.println("原始类型: " + type.getRawType());
			System.out.println("所属类型: " + type.getOwnerType());
			Type[] actualTypeArguments = type.getActualTypeArguments();
			Type oneType = actualTypeArguments[0];
			if(oneType instanceof TypeVariable) {
				System.out.println("这个参数是泛型变量类型!");
				TypeVariable typeVariable = (TypeVariable) oneType;
				System.out.println("变量名称: " + typeVariable.getName());
				System.out.println("变量声明: " + typeVariable.getGenericDeclaration());
				Type[] bounds = typeVariable.getBounds();
				System.out.println("变量上边界数量: " + bounds.length);
				System.out.println("变量上边界清单: ");
				for (Type bound : bounds) {
					System.out.println(bound.getTypeName());
				}
			}
		}
		System.out.println("m1返回参数类型信息-----------------");
		Type returnType = method.getGenericReturnType();
		if(returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			System.out.println("原始类型: " + type.getRawType());
			System.out.println("所属类型: " + type.getOwnerType());
			Type[] actualTypeArguments = type.getActualTypeArguments();
			Type oneType = actualTypeArguments[0];
			if(oneType instanceof TypeVariable) {
				System.out.println("这个参数是泛型变量类型!");
				TypeVariable typeVariable = (TypeVariable) oneType;
				System.out.println("变量名称: " + typeVariable.getName());
				System.out.println("变量声明: " + typeVariable.getGenericDeclaration());
				Type[] bounds = typeVariable.getBounds();
				System.out.println("变量上边界数量: " + bounds.length);
				System.out.println("变量上边界清单: ");
				for (Type bound : bounds) {
					System.out.println(bound.getTypeName());
				}
			}
		}
	}
}
