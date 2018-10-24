package com.yunwoo.cybershop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * 对象操作的一些基本方法，继承自spring
 *
 */
public class ObjectUtil extends ObjectUtils {
	
	protected final static Logger logger = LoggerFactory.getLogger(ObjectUtil.class);
	/**
	 * 判断对象是否为null
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty( Object obj){
		return obj == null;
	}
	/**
	 * 判断对象是否不为null
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty( Object obj){
		return !isEmpty( obj );
	}
	
	public String toString(){
		return toString();
	}
	
}
