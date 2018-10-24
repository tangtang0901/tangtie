package com.yunwoo.cybershop.db;

import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;

import java.io.Serializable;

/**
 * 仓储层扩展基类，包含分页查询方法
 * 适用于需要分页高级查询的场景
 * @param <T> PO实体类
 */
public interface PaginationStorage<T extends Serializable> {
	
	/**
	 * 根据分页查询
	 * @return
	 */
	PaginationResult<T> find(Pagination pagination);
	
}
