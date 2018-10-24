package com.yunwoo.cybershop.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 集合工具类，集成自spring包
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils{
	
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	public static boolean isNotEmpty(Map<?,?> map){
		return !isEmpty(map);
	}
	/**
	 * 根据 map中的 value找 key，<br/>
	 * 使用时请 确保value不重复，且key不包含null，<br/>
	 * 请谨慎使用
	 * @param value
	 * @param map
	 * @return  
	 */
	public static <K,V> K getKeyByValue(V value,Map<K, V> map) {
		if (isNotEmpty(map)) {
			Set<Entry<K, V>> entries = map.entrySet(); 
			if (value == null) {
				for(Entry<K, V> entry : entries) {
					if (entry.getValue() == null) {
						return entry.getKey();
					}
				}
			}
			for(Entry<K, V> entry : entries) {
				if (value.equals(entry.getValue())) {
					return entry.getKey();
				}
			}
		}
		return null;
	}
}
