package com.yunwoo.cybershop.model;

import com.yunwoo.cybershop.utils.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 * @param <T>
 */
public class PaginationResult<T extends Serializable> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7022226040407944171L;

	/**
	 * 当前页码
	 */
	private int pageNum;
	
	/**
	 * 每页条数
	 */
	private int pageSize;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 总记录数
	 */
	private int totalNum;
	
	/**
	 * 数据结果集
	 */
	private List<T> result;
	
	public PaginationResult(){
		
	}
	
	public PaginationResult(int pageNum, int pageSize, int totalNum, List<T> result){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.result = result;
		calculatePage();
	}
	
	public PaginationResult(Pagination pagination, int totalNum, List<T> result){
		this.pageNum = pagination.getPageNum();
		this.pageSize = pagination.getPageSize();
		this.totalNum = totalNum;
		this.result = result;
		calculatePage();
	}
	
	public boolean hasResult(){
		return CollectionUtils.isEmpty(result)?false:true;
	}

	/**  
	 * 获取当前页码  
	 * @return pageNum 当前页码  
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**  
	 * 设置当前页码  
	 * @param pageNum 当前页码  
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**  
	 * 获取每页条数  
	 * @return pageSize 每页条数  
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**  
	 * 设置每页条数  
	 * @param pageSize 每页条数  
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**  
	 * 获取总页数  
	 * @return totalPage 总页数  
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**  
	 * 获取总记录数  
	 * @return totalNum 总记录数  
	 */
	public int getTotalNum() {
		return totalNum;
	}

	/**  
	 * 设置总记录数,会执行计算总页数的方法
	 * @param totalNum 总记录数  
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		calculatePage();
	}

	/**  
	 * 获取数据结果集  
	 * @return result 数据结果集  
	 */
	public List<T> getResult() {
		return result;
	}

	/**  
	 * 设置数据结果集  
	 * @param result 数据结果集  
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}
	
	/**
	 * 计算总页数
	 */
	private void calculatePage(){
		if( totalNum%pageSize == 0 ){
			this.totalPage = totalNum/pageSize;
		}else{
			this.totalPage = totalNum/pageSize+1;
		}
	}
}
