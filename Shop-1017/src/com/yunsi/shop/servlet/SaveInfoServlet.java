package com.yunsi.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveInfoServlet
 */
public class SaveInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(); 
		//获取并保存支付信息到会话中 银行bank  卡号cardid  密码 password
		String bank = request.getParameter("bank");
		String cardid = request.getParameter("cardid");
		String password = request.getParameter("password");
		bank = new String (bank.getBytes("iso-8859-1"),"Utf-8");
		
		//保存金额信息以及选择信息   checkvalue（选择结算的商品 @） gototal（金额）
		String checkvalue =(String) session.getAttribute("checkvalue");
		String gototal =(String) session.getAttribute("gototal");
		
		
		//获取并保存邮寄信息到会话中  checkloc：收件人reuser  电话phone  邮编 postcode 地址 site

		String checkloc =(String) session.getAttribute("checkloc");
		checkloc = new String (checkloc.getBytes("iso-8859-1"),"Utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(
				"<!DOCTYPE html>" + 
				"<html >" + 
				"<head>" + 
				"    <title>结算信息</title>" + 
				"	 <link rel=\"stylesheet\" href=\"./css/main.css\">" + 
				"	 <script src=\"./js/main.js\"></script>" + 
				"</head>" + 
				"<body>" + 
				"<div id=\"detailtable\">" + 
				"    <table>" + 
				"        <caption >购物详细清单</caption>" + 
				"        <tr>" + 
				"            <th colspan=\"4\" align=\"left\">商品清单：</th>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>商品名称</td><td>单价</td><td>数量</td><td>小计</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>合计："+gototal+checkvalue+"￥</td>" + 
				"            <td colspan=\"3\"><span></span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <th colspan=\"4\" align=\"left\">付款信息：</th>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>发卡行：</td>" + 
				"            <td colspan=\"3\"><span>"+bank+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>卡号：</td>" + 
				"            <td colspan=\"3\"><span>"+cardid+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>密码：</td>" + 
				"            <td colspan=\"3\"><span>"+password+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <th colspan=\"4\" align=\"left\">寄件信息：</th>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>收件人：</td>" + 
				"            <td colspan=\"3\"><span>"+checkloc+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>电话号码：</td>" + 
				"            <td colspan=\"3\"><span></span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>邮编：</td>" + 
				"            <td colspan=\"3\"><span></span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>地址：</td>" + 
				"            <td colspan=\"3\"><span></span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td><button>退出应用</button></td>" + 
				"            <td><button>下载清单</button></td>" + 
				"        </tr>" + 
				"    </table>" + 
				"</div>" + 
				"</body>" + 
				"</html>");
		
		
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
