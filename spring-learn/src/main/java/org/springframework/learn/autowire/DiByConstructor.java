package org.springframework.learn.autowire;

/**
 * 按照构造器的方式进行注入
 *
 * spring会找到x类中所有的构造方法（一个类可能有多个构造方法），
 * 然后将这些构造方法进行排序（先按修饰符进行排序，public的在前面，其他的在后面，
 * 如果修饰符一样的，会按照构造函数参数数量倒序，
 * 也就是采用贪婪的模式进行匹配，spring容器会尽量多注入一些需要的对象）得到一个构造函数列表，
 * 会轮询这个构造器列表，判断当前构造器所有参数是否在容器中都可以找到匹配的bean对象，
 * 如果可以找到就使用这个构造器进行注入，
 * 如果不能找到，那么就会跳过这个构造器，继续采用同样的方式匹配下一个构造器，直到找到一个合适的为止。
 * @author zhangcy
 */
public class DiByConstructor {

	private Service1 service1;
	private Service2 service2;

	public DiByConstructor(Service1 service1) {
		this.service1 = service1;
	}

	public DiByConstructor(Service1 service1, Service2 service2) {
		this.service1 = service1;
		this.service2 = service2;
	}

	@Override
	public String toString() {
		return "DiByConstructor{" +
				"service1=" + service1 +
				", service2=" + service2 +
				'}';
	}

	public static class Service2 {
		private String desc;

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return "Service2{" +
					"desc='" + desc + '\'' +
					'}';
		}
	}

	public static class Service1 {
		private String desc;

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return "Service1{" +
					"desc='" + desc + '\'' +
					'}';
		}
	}

}
