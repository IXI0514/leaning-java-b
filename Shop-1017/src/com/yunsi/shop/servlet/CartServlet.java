package com.yunsi.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.shop.bean.ProductInfo;
import com.yunsi.shop.service.IShopService;
import com.yunsi.shop.service.impl.ShopServiceImpl;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private  IShopService iss;
	private static final long serialVersionUID = 1L;

	public CartServlet() {
    	super();
    	iss = new ShopServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		HttpSession session =request.getSession();
		
		pw.println("<html>" + 
				"<head>" + 
				"    <title>购物车</title>" + 
				"    <link rel=\"stylesheet\" href=\"./css/main.css\">" + 
				
				"    <style>" + 
				"    </style>" + 
				"</head>" + 
				"<body >" + 
				" <div>" + 
				"	<table id=\"carttable\" >" + 
				"	        <caption>购物车</caption>" + 
				"	        <tr>"+ 
				"				<td><input name=\"check\" type=\"checkbox\"  onclick=\"checkAll(this)\"/>全选</td>" + 
				"	            <td><img alt=\"\" src=\"\">商品详情</td>" + 
				"	            <td>商品ID</td>" + 
				"	            <td>商品名</td>" + 
				"	            <td>商品单价</td>" + 
				"	            <td>商品数量</td>" + 
				"               <td>操作"+ 
				"				<button type=\"button\" name=\"dele\" onclick=\"dele()\"></button></td>" + 
				"				</td>" + 
				"	        </tr>");
		@SuppressWarnings("unchecked")
		HashMap<ProductInfo, Integer> cart = (HashMap<ProductInfo, Integer>) session.getAttribute("cart");
		Double checkout = 0.0;
		
		System.out.println(cart!=null&&cart.size()>0);
		if(cart!=null&&cart.size()>0) {
			for (Entry<ProductInfo, Integer> entry : cart.entrySet()) {
				ProductInfo entrykey =entry.getKey();
				int entrynum =entry.getValue();
				checkout+=(Double.parseDouble(entrykey.getPprice())*entrynum);
				pw.println("	        <tr>" + 
						"				<td><input name=\"check\" type=\"checkbox\" onchange=\"checktotal()\"/>"+ 
						"				<td><img alt=\"商品详情\" src=\"./image/dog.png\"></td>" + 
						"	            <td>"+entrykey.getPid()+"</td>" + 
						"	            <td>"+entrykey.getPname()+"</td>" + 
						"	            <td>"+entrykey.getPprice()+"</td>" + 
						"	            <td>"+entrynum+"</td>" + 
						"				<td><button type=\"button\" name=\"add\" onclick=\"add(this)\" ></button>" + 
						"				<button type=\"button\" name=\"reduce\" onclick=\"reduce(this)\" ></button>" + 
						"               <button type=\"button\" name=\"dele\" onclick=\"deleme(this)\" ></button></td>"+ 
						"	        </tr>");
				
			}
				
			pw.println("<tr><td><a href=\"ShopServlet\">&nbsp继续购物</a></td><td td colspan=\"6\" align=\"right\">总计："+checkout+"￥&nbsp</td></tr>");
			pw.println("<tr><td colspan=\"6\" align=\"right\" border=\"0\">合计："
						+ "<span id=\"total\">Total&nbsp</span></td>"
						+ "<td align=\"center\"><a href=\"javascript:goCheckout()\">&nbsp结算</a></td></tr>");
			
		}else {
			pw.println("<tr><td colspan=\"7\" >当前没有商品,快去<a href=\"ShopServlet\">购买</a>吧!!</td></tr>");
		}

		pw.println(
				"</table>	" + 
				"</div>" + 
				"</body>" + 
				"<script src=\"./js/main.js\"></script>" + 
				"</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
