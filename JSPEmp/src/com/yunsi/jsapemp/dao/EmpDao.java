package com.yunsi.jsapemp.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yunsi.jsapemp.bean.Emp;
import com.yunsi.jsapemp.bean.Page;

public class EmpDao {

	public List<Emp> getEmps() {
		String sql = "select empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal from empdemo";
		
		try {
			List<Emp> list = DBTools.getQr().query(sql, new BeanListHandler<Emp>(Emp.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int dele(String empno) {
		String sql = "delete from empdemo where empno=?";
		try {
			System.out.println(empno);
			int result =DBTools.getQr().update(sql, empno);
			//System.out.println(result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int additem(Emp emp) {
		String sql = "insert into  empdemo(ename,job,hiredate,sal) values(?,?,to_date(?,'yyyy-mm-dd'),?)";
		try {
			
			int result =DBTools.getQr().update(sql, emp.getEname(),emp.getJob(),emp.getHiredate(),emp.getSal());
			//System.out.println(result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Page getEmps(Page page) {
		//获取总数
		String sql = "select count(*) from empdemo";
		
		Page newpage = new Page();
		int size = page.getPagesize();//页大小
		newpage.setPagesize(size);
		int p = page.getPage();//页码
		System.out.print("page"+p);
		newpage.setPage(p);
		try {
			int count=DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>()).intValue();
			newpage.setCount(count);
			
			String sql2 ="	select empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal " + 
					"	from " + 
					"		(select rownum r,e.* " + 
					"				from (select * from empdemo order by empno) e where rownum<=?)" + 
					"						 where r>=?";
			System.out.println("db"+size*p+"--"+(p-1)*size);
			List<Emp> list = DBTools.getQr().query(sql2, new BeanListHandler<Emp>(Emp.class),size*p,(p-1)*size+1);
			newpage.setList(list);
			//System.out.println("dao:"+newpage.getList());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newpage;
				
		
	}
	
}
