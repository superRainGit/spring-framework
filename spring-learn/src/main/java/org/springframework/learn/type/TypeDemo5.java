package org.springframework.learn.type;

/**
 * @author zhangcy
 */
public class TypeDemo5<T1, T2> {

	public void m1(TypeDemo5<T1, T2> demo) {
		System.out.println(demo.getClass());
	}

	public static void main(String[] args) {
		TypeDemo5<String, Integer> demo5 = new TypeDemo5<>();
		demo5.m1(demo5);
	}
}
