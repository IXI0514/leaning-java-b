package com.yunsi.beans;

import java.util.List;

public class PageBean {
	private int currentPage;//当前页 
	private int totalpage;//总页数  iteamsum/pageSize
	private int pageSize;//单页面数量  1
	private int itemsum;//总记录数
	private List<Question> list;//20数据 dao
	
	
	
	public PageBean() {
		super();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalpage() {
		return totalpage;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getItemsum() {
		return itemsum;
	}
	
	public void setItemsum(int itemsum) {
		this.itemsum = itemsum;
		this.totalpage=(itemsum%pageSize==0)?itemsum/pageSize:itemsum/pageSize+1;
	}
	public List<Question> getList() {
		return list;
	}
	public void setList(List<Question> list) {
		this.list = list;
	}
	
	
	
	
	
}
