package com.ccm.api.dao.pms.utils;


import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;


public class DBConnection {
	private static Connection conn;
	
	@Resource
	private static DataSource dataSource;
	
	public DBConnection(){
		
//		try {
//			/*
//			DataSource ds = null;
//			InitialContext itx = new InitialContext();
//			ds = (DataSource) itx.lookup("java:comp/env/jdbc/mysql");
//			*/
//			Connection conn = dataSource.getConnection();
//			
//			Statement stat = conn.createStatement();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
//	public static Connection getConnection(){
//		if(conn == null){
//			try {
//				DataSource ds = null;
//				InitialContext itx = new InitialContext();
//				ds = (DataSource) itx.lookup("java:comp/env/jdbc/mysql");
//				conn = ds.getConnection();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return conn;
//	}
	
//	public static Statement getStatement(){
//		try {
//			return getConnection().createStatement();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	public static void setDataSource(DataSource ds){
		dataSource = ds;
	}
	public static DataSource getDataSource(){
//		DataSource ds = null;
//		try{
//			InitialContext itx = new InitialContext();
//			ds = (DataSource) itx.lookup("java:comp/env/jdbc/mysql");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return ds;
		return dataSource;
	}
//	public static QueryRunner getQueryRunner(){
//		return new QueryRunner(dataSource);
//	}
}
