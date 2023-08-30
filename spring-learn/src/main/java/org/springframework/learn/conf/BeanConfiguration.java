package org.springframework.learn.conf;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.learn.impl.LearnSpringImpl;
import org.springframework.learn.interfaces.ILearnSpringInterface;
import org.springframework.learn.processor.SelfBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * bean configuration
 * @author zhangcy
 */
// @Component
public class BeanConfiguration {

	@Bean
	public ILearnSpringInterface learnSpring() {
		return new LearnSpringImpl();
	}

	@Bean
	public BeanPostProcessor selfBeanPostProcessor() {
		return new SelfBeanPostProcessor();
	}

}
