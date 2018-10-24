package com.yunwoo.cybershop.utils;


import com.yunwoo.cybershop.model.PaginationResult;
import com.yunwoo.cybershop.model.PaginationResult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * XML返回结果处理工具类
 * @author ALI 2017年3月21日
 *
 */
public class XMLResultUtil {

	/**
	 * 返回操作的结果XML
	 * @param data 响应数据
	 * @return
	 */
	public static <T> String object2XML(T data){
		return null;
	}
	
	/**
	 * 返回集合XML数据
	 * @return
	 */
	public static String list2XML(List<? extends Serializable> result){
		return null;
	}
	
	/**
	 * 返回map XML数据
	 * @return
	 */
	public static String map2XML(Map<? extends Serializable, ? extends Serializable> result){
		return null;
	}
	
	/**
	 * 返回列表页XML数据
	 * @param paginationResult
	 * @return
	 */
	public static String dataGrid2XML(PaginationResult<? extends Serializable> paginationResult){
		return null;
	}

	
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(123, "fddsfsdff");
		map.put(34243, "fddsfsdfdsff");
		map.put(453, "fdd44554sfsdff");
//		List<String> list = new ArrayList<String>();
		System.out.println(map2XML(map));
	}
}
