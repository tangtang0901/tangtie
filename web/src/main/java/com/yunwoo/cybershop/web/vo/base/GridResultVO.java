package com.yunwoo.cybershop.web.vo.base;

import java.util.List;

public class GridResultVO {
	
	/**
	 *  总数
	 */
	private int total;
	
	/**
	 * 数据
	 */
	private List data;
	
	public GridResultVO(int total, List data) {
		super();
		this.total = total;
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
	
	
	
	
}
