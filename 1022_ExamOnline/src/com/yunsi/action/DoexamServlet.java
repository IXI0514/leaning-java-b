package com.yunsi.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.beans.PageBean;
import com.yunsi.beans.Question;
import com.yunsi.dao.IExamDao;
import com.yunsi.dao.impl.ExamDaoimpl;
import com.yunsi.service.IExamService;
import com.yunsi.service.impl.ExamServiceImpl;

/**
 * Servlet implementation class DoexamServlet
 */
@WebServlet("/exam/goexam")
public class DoexamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoexamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//../exam/doexam?turnpage=back
		String turn =request.getParameter("turnpage");
		HttpSession session = request.getSession();
		PageBean pb = (PageBean) session.getAttribute("pagebean");
		if( turn.equals("back")) {
				pb.setCurrentPage(pb.getCurrentPage()-1);
		}else if(turn.equals("go")) {
			pb.setCurrentPage(pb.getCurrentPage()+1);
		}
		session.setAttribute("pagebean", pb);
		response.sendRedirect("../jsp/exam.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建pagebean
		PageBean pb = new PageBean();
		pb.setCurrentPage(0);
		pb.setPageSize(1);
	
		String count =request.getParameter("requsetsum");
		pb.setItemsum(Integer.parseInt(count));	
		
		//获取随机题目
		IExamService ied = new ExamServiceImpl();
		List<Question> list= ied.getRandomExam(Integer.parseInt(count));
		//将题目存到session中
		pb.setList(list);
		HttpSession session = request.getSession();
		session.setAttribute("pagebean", pb);
		response.sendRedirect("../jsp/exam.jsp");
	}

}
