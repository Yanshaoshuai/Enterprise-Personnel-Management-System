package com.util;

//import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlHelper {
	private static Connection conn=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	//private static CallableStatement cs=null;
	
	/**
	 * ͳһ��ѯ����
	 * @param sql
	 * @param parameters
	 * @return	����ArrayList<Object[]>
	 */
	public static ArrayList<Object[]>  executeQuery(String sql,String[]parameters){
		ArrayList<Object[]> al=new ArrayList<Object[]>();
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			if(parameters!=null){
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i+1, parameters[i]);//��1��ʼ
				}
			}
			rs=ps.executeQuery();
			ResultSetMetaData rsm;
			int  columnNum;
			if(rs!=null){
				rsm=rs.getMetaData();
				columnNum=rsm.getColumnCount();
				while(rs.next()){
					Object[] objects=new Object[columnNum];
					for (int i = 0; i < objects.length; i++) {
						objects[i]=rs.getObject(i+1);//��1��ʼ
					}
					al.add(objects);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, conn, ps);
		}
		
		return al;
	}
	/**
	 * ͳһ�������ɾ���ķ���������������
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static boolean executeUpdate(String sql,String[]parameters){
		boolean success =false;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			if(parameters!=null){
				for (int i = 0; i < parameters.length; i++) {
					
					ps.setObject(i+1, parameters[i]);
				}
			}
			int n=ps.executeUpdate();
			if(n>0){
				success=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(null, conn, ps);
		}
		
		return success;
	}
	/**
	 * ͳһ�Ĳ���ɾ�����²���,��Ҫ��������
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static void executeUpdate(String[] sql,String[][]parameters){
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			if(sql!=null){
				for (int i = 0; i < sql.length; i++) {
					ps=conn.prepareStatement(sql[i]);
					if(parameters!=null){
						for (int j = 0; j < parameters[i].length; j++) {//��i��sql����и�����
							ps.setObject(j+1, parameters[i][j]);
						}
					}
					ps.executeUpdate();
				}
			}
			
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				//
				e1.printStackTrace();
			}
		}finally{
			DBUtil.closeAll(null, conn, ps);
		}
	}
	
}
