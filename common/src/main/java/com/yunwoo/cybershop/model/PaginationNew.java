package com.yunwoo.cybershop.model;

/**新的pagination主要用配合前端框架
 * @author Fox
 * 
 */
public class PaginationNew {
	private String keyField;
	private String keyValue;
	private int pageIndex;
	private int pageSize;
	private String sortField;
	private String sortOrder;
	private int startNum;
	
	public String getKeyField() {
		return keyField;
	}
	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		if(pageIndex < 0 ){
			pageIndex = 0;
		}
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getStartNum() {
		this.startNum = this.pageIndex*this.pageSize;
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	

}
