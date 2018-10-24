package com.yunsi.jsapemp.service;

import java.util.List;

import com.yunsi.jsapemp.bean.Emp;
import com.yunsi.jsapemp.bean.Page;
import com.yunsi.jsapemp.dao.EmpDao;

public class EmpService {
	private  EmpDao ed;
	
	public EmpService() {
		ed = new EmpDao();
		// TODO Auto-generated constructor stub
	}
	

	public List<Emp> getEmps() {
		// TODO Auto-generated method stub
	
		return ed.getEmps();
	}


	public int dele(String empno) {
		// TODO Auto-generated method stub
		 return ed.dele(empno);
	}


	public int deleitems(String str) {
		String[] empnos = str.split("@");
		//int len = empnos.length;
		int result=0;
		for (String empno:empnos) {
			int s =ed.dele(empno);
			if(s==-1) {
				return -1;
			}else {
				result+=s;
			}
		}
		return result;
	}


	public int additem(Emp emp) {
		// TODO Auto-generated method stub
		return ed.additem(emp);
	}


	public Page getEmps(Page page) {

		return ed.getEmps(page);
	}

}
