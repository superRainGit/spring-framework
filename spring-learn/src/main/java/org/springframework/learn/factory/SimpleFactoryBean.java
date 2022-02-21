package org.springframework.learn.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.learn.bean.UserModel;

/**
 * 使用实现FactoryBean接口的方式去构建对应的对象
 * @author zhangcy
 */
public class SimpleFactoryBean implements FactoryBean<UserModel> {

	@Override
	public UserModel getObject() throws Exception {
		return new UserModel();
	}

	@Override
	public Class<?> getObjectType() {
		return UserModel.class;
	}
}
