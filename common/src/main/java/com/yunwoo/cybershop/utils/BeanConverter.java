package com.yunwoo.cybershop.utils;

import com.yunwoo.cybershop.exception.PaginationResultParamException;
import com.yunwoo.cybershop.model.PaginationResult;
import com.yunwoo.cybershop.exception.PaginationResultParamException;
import com.yunwoo.cybershop.model.PaginationResult;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 简单对象转换工具类
 * @author ALI
 */

public  class BeanConverter {

	
	public static<T,S> T to(S source,Class<T> t) {
		T target = null;
		if (source != null) {
			try {
				target = t.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BeanUtils.copyProperties(source, target);
		}
		return target;
	}

	
	public static<T,S>  List<T> to(List<S> source,Class<T> t) {

		if (source == null) {
			return null;
		}

		List<T> targets = getTargetList(source.size());

		for (S s : source) {
			targets.add(to(s,t));
		}

		return targets;
	}

	public static<T,S>  Map<String,T> to(Map<String,S> source,Class<T> t) {

		if (source == null) {
			return null;
		}

		Map<String,T> targets = new HashMap<String, T>();

		for (Entry<String, S> entry : source.entrySet()) {
			targets.put(entry.getKey(), to(entry.getValue(),t));
		}

		return targets;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static<T>  List<T> getTargetList(int size){
		return new ArrayList(size);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static<T extends Serializable> PaginationResult<T> getTargetPaginationResult(int pageNum, int pageSize, int totalNum, List<T> result) throws PaginationResultParamException {
		return new PaginationResult(pageNum, pageSize, totalNum, result);
	}
	

	public static<T extends Serializable,S extends Serializable>  PaginationResult<T> to(PaginationResult<S> source,Class<T> t) {
		
		if(source == null) {
			return null;
		}
		
		List<T> list= to(source.getResult(),t);
		try{
			return getTargetPaginationResult(source.getPageNum(), source.getPageSize(), source.getTotalNum(), list);
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}

	}
	

}
