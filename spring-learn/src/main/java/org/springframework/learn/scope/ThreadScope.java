package org.springframework.learn.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义线程scope 同一个线程内部获取bean是一致的
 * @author zhangcy
 */
public class ThreadScope implements Scope {

	public static String SCOPE_NAME = "thread";

	private ThreadLocal<Map<String, Object>> mapThreadLocal = ThreadLocal.withInitial(() -> new HashMap<>(2));

	/**
	 * 如何获取对应的scope下的bean实例
	 * @param name the name of the object to retrieve
	 * @param objectFactory the {@link ObjectFactory} to use to create the scoped
	 * object if it is not present in the underlying storage mechanism
	 * @return 对应的bean的实例
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String, Object> stringObjectMap = mapThreadLocal.get();
		Object o = stringObjectMap.get(name);
		if(o == null) {
			o = objectFactory.getObject();
			stringObjectMap.put(name, o);
		}
		return o;
	}

	/**
	 * 将name对应的bean从当前作用域中移除
	 * @param name the name of the object to remove
	 */
	@Override
	public Object remove(String name) {
		Map<String, Object> stringObjectMap = mapThreadLocal.get();
		return stringObjectMap.remove(name);
	}

	/**
	 * 用于注册销毁回调，如果想要销毁相应的对象,则由Spring容器注册相应的销毁回调，而由自定义作用域选择是不是要销毁相应的对象
	 * @param name the name of the object to execute the destruction callback for
	 * @param callback the destruction callback to be executed.
	 */
	@Override
	public void registerDestructionCallback(String name, Runnable callback) {

	}

	/**
	 * 用于解析相应的上下文数据，比如request作用域将返回request中的属性
	 * @param key the contextual key
	 */
	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	/**
	 * 作用域的会话标识，比如session作用域将是sessionId
	 */
	@Override
	public String getConversationId() {
		return Thread.currentThread().getName();
	}
}
