package com.yunsi.jsapemp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yunsi.jsapemp.bean.Emp;
import com.yunsi.jsapemp.service.EmpService;

/**
 * Servlet implementation class AddempServlet
 */
@WebServlet("/add")
public class AddempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddempServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ename =request.getParameter("ename");
		String job =request.getParameter("ejob");
		String hiredate =request.getParameter("ehiredate");
		String sal =request.getParameter("esal");
		Emp emp = new Emp(ename, job, hiredate, sal);
		EmpService es = new EmpService();
		int result =es.additem(emp);
		String msg = null; 
		if(result>0) {
			msg="添加成功！！";
			System.out.println(msg);
		}else {
			msg="添加出错！！";
			System.out.println(msg);
		}
		System.out.println(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//response.sendRedirect("list");
		PrintWriter pw = response.getWriter();
		//pw.println("123");
		pw.println("<script>alert('"+msg+"');window.location.href='list?turnpage=firstpage';</script>");
		//pw.println("123");
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
