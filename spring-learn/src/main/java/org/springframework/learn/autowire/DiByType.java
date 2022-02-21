package org.springframework.learn.autowire;

/**
 * 按照类型自动装配
 * @author zhangcy
 */
public class DiByType {

	private Service1 service1;

	private Service2 service2;

	public void setService1(Service1 service1) {
		this.service1 = service1;
	}

	public void setService2(Service2 service2) {
		this.service2 = service2;
	}

	@Override
	public String toString() {
		return "DiByType{" +
				"service1=" + service1 +
				", service2=" + service2 +
				'}';
	}

	public static class Service2 {

		private String decs;

		public void setDecs(String decs) {
			this.decs = decs;
		}

		public String getDecs() {
			return decs;
		}

		@Override
		public String toString() {
			return "Service2{" +
					"decs='" + decs + '\'' +
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
