package org.springframework.learn.autowire;

import java.util.List;
import java.util.Map;

/**
 * 按照类型注入的扩展示例
 * @author zhangcy
 */
public class DiByTypeExtend {

	private interface IService {}

	public static class BaseService implements IService {

		private String desc;

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return "BaseService{" +
					"desc='" + desc + '\'' +
					'}';
		}
	}

	public static class Service1 extends BaseService {}

	public static class Service2 implements IService {
		@Override
		public String toString() {
			return "service2";
		}
	}

	/**
	 * 按照类型自动装配的情况 按照list或者map中对应的数据类型进行匹配
	 */
	private List<IService> serviceList;
	private List<BaseService> baseServiceList;
	private Map<String, IService> serviceMap;
	private Map<String, BaseService> baseServiceMap;

	public void setServiceList(List<IService> serviceList) {
		this.serviceList = serviceList;
	}

	public void setBaseServiceList(List<BaseService> baseServiceList) {
		this.baseServiceList = baseServiceList;
	}

	public void setServiceMap(Map<String, IService> serviceMap) {
		this.serviceMap = serviceMap;
	}

	public void setBaseServiceMap(Map<String, BaseService> baseServiceMap) {
		this.baseServiceMap = baseServiceMap;
	}

	@Override
	public String toString() {
		return "DiByTypeExtend{" +
				"serviceList=" + serviceList +
				", baseServiceList=" + baseServiceList +
				", serviceMap=" + serviceMap +
				", baseServiceMap=" + baseServiceMap +
				'}';
	}
}
