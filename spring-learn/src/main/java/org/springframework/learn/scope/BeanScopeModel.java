package org.springframework.learn.scope;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * BeanScopeModel
 * @author zhangcy
 */
public class BeanScopeModel {

	private Log logger = LogFactory.getLog(getClass());

	public BeanScopeModel(String scope) {
		logger.debug("invoke with " + scope + " and bean = " + this);
	}
}
