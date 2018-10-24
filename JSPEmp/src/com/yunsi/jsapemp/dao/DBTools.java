package com.yunsi.jsapemp.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class DBTools {
	private static QueryRunner qr;
	
	static {
		try {
			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/ordb");//META-INF/context.xml
			qr = new QueryRunner(dataSource);
		} catch (NamingException e) {
			e.printStackTrace();
			
			//没有获取到tomcat中的配置文件
			System.out.println("JNDI获取失败...");
			BasicDataSource bds = new BasicDataSource();
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				bds.setDriverClassName("oracle.jdbc.OracleDriver");
				bds.setUsername("scott");
				bds.setPassword("scott");
				bds.setUrl("jdbc:oracle:thin:@localhost:1521:ordb");
				qr = new QueryRunner(bds);
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			}
		}
	}
	public static QueryRunner getQr() {
		return qr;
	}
	
	
	
}
