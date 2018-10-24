package com.yunsi.jsapemp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yunsi.jsapemp.service.EmpService;

/**
 * Servlet implementation class Delectemp
 */
@WebServlet("/dele")
public class Delectemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delectemp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empno = request.getParameter("empno");
		System.out.println("请求删除："+empno);
		EmpService es = new EmpService();
		int result =es.dele(empno);
		System.out.println(result);
		String msg = "删除失败"; 
		if(result==1) {
			msg="删除成功！！";
			
		}else if(result==0){
			msg="删除失败";
		}else {
			msg="删除出错！！";
		}
		System.out.println(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<script>alert('"+msg+"');window.location.href='list?turnpage=firstpage';</script>");
		pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empnos = request.getParameter("empno");
		System.out.println("请求删除："+empnos);
		EmpService es = new EmpService();
		int result =es.deleitems(empnos);
		String msg = null; 
		if(result>0) {
			msg="删除成功！！\n已删除"+result+"行";
			System.out.println(msg);
		}else {
			msg="删除出错！！";
			System.out.println(msg);
		}
		System.out.println(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.sendRedirect("list?turnpage=firstpage");
		/*PrintWriter pw = response.getWriter();
		pw.println("123");
		pw.println("<script>alert('"+msg+"');window.location.href='list';</script>");
		pw.println("123");
		pw.close();*/
	}

}
