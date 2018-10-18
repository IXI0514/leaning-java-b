package com.yunsi.shop.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yunsi.shop.bean.ProductInfo;
import com.yunsi.shop.bean.UserBean;
import com.yunsi.shop.dao.IShopDao;
import com.yunsi.shop.service.impl.ShopServiceImpl;



public class ShopDaoImpl implements IShopDao{

	@Override
	public int loginByNickname(UserBean user) {
		String sql = "select count(*) from logininfo where username =? and password =?";
		try {
			BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>(),user.getUsername(),user.getPassword());
			System.out.println(db.intValue());
			return db.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return -1;
	}

	@Override
	public int loginByUid(UserBean user) {
		String sql = "select count(*) from logininfo where userid =100000 and password ='123'";
		try {
			/*System.out.println(Integer.parseInt(user.getUid()));
			BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>(),Integer.parseInt(user.getUid()),user.getPassword());*/
			BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>());
			System.out.println(db.intValue());
			return db.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return -1;
	}

	private List<ProductInfo> getInfo() {
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(ShopServiceImpl.class.getResourceAsStream("/goods.txt")));
			String line = null;
			while((line=br.readLine())!=null) {
				String[] strs =line.trim().split("\\s+");
				if(strs.length==4) {
					ProductInfo p = new ProductInfo(strs[0], strs[1], strs[2],strs[3]);
					list.add(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ProductInfo getProductInfo(String pid) {
		
		 List<ProductInfo> infos = this.getInfo();
		 if(infos!=null&&infos.size()>0) {
			 Iterator<ProductInfo> it = infos.iterator();
			 while(it.hasNext()) {
				 ProductInfo  p = it.next();
				 if(p.getPid().equals(pid)) {
					 return p;
				 }
			 }
		 }
		return null;
	}
	public static void main(String[] args) {
		System.out.println(new ShopDaoImpl().getProductInfo("001"));;
	
		
	}
}
