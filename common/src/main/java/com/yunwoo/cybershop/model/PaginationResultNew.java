package com.yunwoo.cybershop.model;

import java.util.List;

/**新的分页结果，主要配合前端使用
 * @author Fox
 * @param <T>
 *
 */
public class PaginationResultNew<T> {
	private int total;
	private List<T> data;
	public PaginationResultNew() {
	}
	public PaginationResultNew(int total, List<T> data) {
		this.total = total;
		this.data = data;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

}
