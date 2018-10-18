package com.yunsi.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yunsi.shop.service.IShopService;
import com.yunsi.shop.service.impl.ShopServiceImpl;



/**
 * 登录响应的servlet
 * @author ShenBL
 *
 */
public class LoginServlet extends HttpServlet {
	private IShopService iss;
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        iss = new ShopServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Servlet....");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名："+username+"密码："+password);
		int result =iss.login(username, password);;
	
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		//result= 1 登录成功 跳转界面
		if(result==1) {
			pw.println ("<script>alert('登录成功...');window.location='ShopServlet'</script>");
			
		}
		//result= 0 登录失败 
		else if (result ==0) {
			pw.println ("<script>alert('用户名密码b...');window.location='index.html'</script>");
		}
		//resulr = -1 登录错误
		else {
			pw.println ("<script>alert('服务端出错...');window.location='index.html'</script>");
		}
		pw.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
