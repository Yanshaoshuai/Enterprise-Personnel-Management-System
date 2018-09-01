package com.util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBUtil {
	private static String url=null;
	private static String username=null;
	private  static String password=null;
	private static String driver=null;
	private static InputStream	is=null;
	static {
		try {
			Properties pp=new Properties();
			is=DBUtil.class.getClassLoader().getResourceAsStream("com/util/dbinfo.properties");
			pp.load(is);
			url=pp.getProperty("url");
			username=pp.getProperty("username");
			password=pp.getProperty("password");
			driver=pp.getProperty("driver");
			Class.forName(driver.trim());
		} catch (Exception e) {
			System.out.println("加载数据库驱动失败");
		}finally{
			if(is!=null){
				try {
					is.close();
					is=null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
	           conn= DriverManager.getConnection(url.trim(), username.trim(),password.trim());

	       } catch (SQLException e) {
	    	   System.out.println("连接失败");
	           e.printStackTrace();

	       }
		
		return conn;
	}
	public static void closeAll(ResultSet rs,Connection conn,Statement st){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
				conn=null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
				st=null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		getConnection();
	}
}
