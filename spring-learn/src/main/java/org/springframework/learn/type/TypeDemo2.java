package org.springframework.learn.type;

import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 方法中定义的泛型变量
 * @author zhangcy
 */
public class TypeDemo2 {

	public <T1, T2 extends Integer, T3 extends Demo21 & Demo22> T3 m1(T1 t1, T2 t2, T3 t3, String s) {
		return t3;
	}

	public static void main(String[] args) {
		Method[] declaredMethods = TypeDemo2.class.getDeclaredMethods();
		Method m1 = null;
		for (Method declaredMethod : declaredMethods) {
			if("m1".equals(declaredMethod.getName())) {
				m1 = declaredMethod;
				break;
			}
		}
		Assert.notNull(m1, "not null");
		System.out.println("m1方法参数类型列表信息:-------------");
		System.out.println("调用getTypeParameters");
		// 获取方法的参数名
		Type[] genericParameterTypes = m1.getGenericParameterTypes();
		for (Type genericParameterType : genericParameterTypes) {
			if(genericParameterType instanceof TypeVariable) {
//				TypeVariable typeVariable = (TypeVariable) genericParameterType;
//				// 如果是泛型化参数
//				System.out.println("变量类型名称: " + typeVariable.getTypeName());
//				System.out.println("变量名称: " + typeVariable.getName());
//				System.out.println("变量在哪里声明: " + typeVariable.getGenericDeclaration());
//				Type[] bounds = typeVariable.getBounds();
//				System.out.println("这个变量的上界数量: " + bounds.length);
//				System.out.println("这个变量的上界清单: ");
//				for (Type bound : bounds) {
//					System.out.println(bound.getTypeName());
//				}
			} else if(genericParameterType instanceof Class) {
				// 标准参数
//				Class pt = (Class) genericParameterType;
//				System.out.println("参数类型名称: " + pt.getTypeName());
//				System.out.println("参数类名: " + pt.getName());
			}
			System.out.println("---------------------------");
		}
		// 获取方法的返回值
		Type genericReturnType = m1.getGenericReturnType();
		if(genericReturnType instanceof TypeVariable) {
//			TypeVariable typeVariable = (TypeVariable) genericReturnType;
//			System.out.println("变量类型名称: " + typeVariable.getTypeName());
//			System.out.println("变量名称: " + typeVariable.getName());
//			System.out.println("变量在哪里声明: " + typeVariable.getGenericDeclaration());
//			Type[] bounds = typeVariable.getBounds();
//			System.out.println("这个变量的上界数量: " + bounds.length);
//			System.out.println("这个变量的上界清单: ");
//			for (Type bound : bounds) {
//				System.out.println(bound.getTypeName());
//			}
//			System.out.println("---------------------------");
		}
		TypeVariable<Method>[] typeParameters = m1.getTypeParameters();
		for (TypeVariable<Method> typeParameter : typeParameters) {
			System.out.println("变量类型名称: " + typeParameter.getTypeName());
			System.out.println("变量名称: " + typeParameter.getName());
			System.out.println("变量在哪里声明: " + typeParameter.getGenericDeclaration());
			Type[] bounds = typeParameter.getBounds();
			System.out.println("这个变量的上界数量: " + bounds.length);
			System.out.println("这个变量的上界清单: ");
			for (Type bound : bounds) {
				System.out.println(bound.getTypeName());
			}
			System.out.println("---------------------------");
		}
	}
}

interface Demo21 {}

interface Demo22 {}
