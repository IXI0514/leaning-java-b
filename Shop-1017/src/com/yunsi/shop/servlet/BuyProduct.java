package com.yunsi.shop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.shop.bean.ProductInfo;

public class BuyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuyProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取购物车 会话
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<ProductInfo, Integer> cart = (HashMap<ProductInfo, Integer>) session.getAttribute("cart");
		if(cart==null) {
			//为用户建立"购物车" 会话
			cart = new HashMap<ProductInfo, Integer>();
			session.setAttribute("cart",cart);
			
		}
		System.out.println(cart==null);
		//获取购买商品的id 以及数量
		String pid =request.getParameter("pid");
		String pnum =request.getParameter("pnum");
		
		ServletContext scont = this.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String,ProductInfo> list  =(HashMap<String,ProductInfo>) scont.getAttribute("plist");
		ProductInfo buyp= list.get(pid);
		System.out.println("buyp:"+buyp);
		
		
		//普通购买 添加一个
		System.out.println("["+pnum+"]");
		if(pnum==null) {
			//System.out.println("存在吗？："+cart.containsKey(buyp));
			Integer cnum= cart.get(buyp);
			if(cnum!=null) {
				cart.put(buyp, cnum+1);
				System.out.println("cart.size:"+cart.size());
			}else {
				cart.put(buyp, 1);
			}
			System.out.println("cart.size:"+cart.size());
			response.sendRedirect("ShopServlet");
		//购物车中修改
		}else {
			response.sendRedirect("CartServlet");
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
