package com.yunsi.jsapemp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

import com.yunsi.jsapemp.bean.Emp;
import com.yunsi.jsapemp.bean.Page;
import com.yunsi.jsapemp.service.EmpService;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListServlet() {
        super();
    }
    @Override
    public void init() throws ServletException {
    	Page page = new Page();
    	page.setPagesize(5);
    	ServletContext sc= this.getServletContext();
    	
    	//读取第一页
    	page.setPage(1);//设置当前页为1
    	EmpService es = new EmpService();
		page= es.getEmps(page);
    	System.out.println(page);
    	sc.setAttribute("page", page);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Page page =(Page) session.getAttribute("page");
		System.out.println(page);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw =response.getWriter();
		//判断页数？
		if(page==null) {
			ServletContext sc= request.getServletContext();
			page =(Page)sc.getAttribute("page");
			System.out.println(page);
			int size=page.getPagesize();
			System.out.println(page);
			//第一页
			System.out.println(page.getCount());
			session.setAttribute("page", page);
			request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
			
			//response.sendRedirect("jsp/list.jsp");
		}else {
			int pnum= page.getPage();
			int num=page.getPagenumber();
			String turn =request.getParameter("turnpage");
			EmpService es = new EmpService();
			System.out.println(turn);
			System.out.println("页数："+pnum+":"+num);
			if(turn!=null) {
				if(turn.equals("firstpage")) {
					page.setPage(1);
					page = es.getEmps(page);
					session.setAttribute("page", page);
					response.sendRedirect("list");
				}
				else if(turn.equals("endpage")) {
					page.setPage(page.getPagenumber());
					page = es.getEmps(page);
					session.setAttribute("page", page);
					response.sendRedirect("list");
				}
				else 
					if(turn.equals("forward")){
						if(pnum<num) {
							page.setPage(pnum+1);
							page = es.getEmps(page);
							session.setAttribute("page", page);
							
						}else {
							pw.println("<script>alert('已经是最后一页');window.location.href='list';</script>");
						}
					}else if(turn.equals("back")){
						
						if(pnum>1) {
							page.setPage(pnum-1);
							page = es.getEmps(page);
							session.setAttribute("page", page);
							response.sendRedirect("list");
						}else {
							System.out.println("已经是第一页");
							pw.println("<script>alert('已经是第一页') ; window.location.href='list';</script>");
						}
					}
					/*EmpService es = new EmpService();
					page = es.getEmps(page);
					//System.out.println("servlet"+page.getList());
					session.setAttribute("page", page);
					response.sendRedirect("list");
					//request.getRequestDispatcher("list").forward(request, response);
					 */		
			
							
			}else {
				System.out.println("gojsp");
				request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
			}
		}
		
		//request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}
