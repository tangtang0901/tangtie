package com.yunwoo.cybershop.db;


import java.io.Serializable;
import java.util.List;

/**
 * 仓储层基类，包含基本的增删改查
 * 适用于不需要分页查询的场景
 * @param <T> 实体类
 */
public interface BaseStorage<T extends Serializable> {
	
	/**
	 * 增加一条记录
	 * @param t
	 */
	boolean add(T t);
	
	/**
	 * 删除一条记录
	 * @param t
	 */
	boolean delete(int id);
	
	/**
	 * 修改一条记录
	 * @param t
	 */
	boolean update(T t);
	
	/**
	 * 根据id查询一条记录
	 * @param id
	 * @return
	 */
	T get(int id);




	
}

