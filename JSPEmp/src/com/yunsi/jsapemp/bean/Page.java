package com.yunsi.jsapemp.bean;

import java.util.List;

public class Page {

	   
    private int page;//当前页 
    private int pagesize;//页面大小 5
    private int count;//总数据条数   dao
    private int pagenumber;//总页面数 dao
    private List<Emp> list; //dao
	
    
    
    @Override
	public String toString() {
		return "Page [page=" + page + ", pagesize=" + pagesize + ", count=" + count + ", pagenumber=" + pagenumber
				+ ", list=" + list + "]";
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		this.pagenumber=(count%pagesize==0)?count/pagesize:count/pagesize+1;
	}
	public int getPagenumber() {
		return pagenumber;
	}
	
	public List<Emp> getList() {
		return list;
	}
	public void setList(List<Emp> list) {
		this.list = list;
	}
    
    
 
       
   

}
	
