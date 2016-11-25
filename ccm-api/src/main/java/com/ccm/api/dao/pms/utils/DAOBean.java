package com.ccm.api.dao.pms.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;


public class DAOBean {
	private static QueryRunner query;

	public static QueryRunner getQueryRunner() {
		if (query == null) {
			query = new QueryRunner(DBConnection.getDataSource());
		}
		return query;
	}
	
	public static List<Object[]> getResultList(String sql, Object... params) throws SQLException {
		try {
			return query(sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.getResultList", e);
			throw e;
		}
	}
	
	public static Object[] getResult(String sql, Object... params) throws SQLException {
		try {
			return query(sql, new ArrayHandler(), params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.getResult", e);
			throw e;
		}
	}
	
	public static List<Map<String,Object>> getResultMapList(String sql, Object... params) throws SQLException {
		try {
			return query(sql, new MapListHandler(), params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.getResultMapList", e);
			throw e;
		}
	}
	
	public static Map<String,Object> getResultMap(String sql, Object... params) throws SQLException {
		try {
			return query(sql, new MapHandler(), params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.getResultMap", e);
			throw e;
		}
	}
	
	public static int update(String sql, Object... params) throws SQLException{
		try {
			return executeUpdate(sql, params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.update", e);
			throw e;
		}
	}
	
	public static int insert(String sql, Object... params) throws SQLException{
		try {
			return executeUpdate(sql, params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.insert", e);
			throw e;
		}
	}
	
	public static int delete(String sql, Object... params) throws SQLException{
		try {
			return executeUpdate(sql, params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.delete", e);
			throw e;
		}
	}
	
	public static int[] updateBatch(String sql,Object[][] params) throws SQLException{
		try {
			return batch(sql, params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.updateBatch", e);
			throw e;
		}
	}
	
	public static int[] insertBatch(String sql,Object[][] params) throws SQLException{
		try {
			return batch(sql, params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.insertBatch", e);
			throw e;
		}
	}
	
	public static int[] deleteBatch(String sql,Object[][] params) throws SQLException{
		try {
			return batch(sql, params);
		} catch (SQLException e) {
			LogUtils.writeLog("DAOBean.deleteBatch", e);
			throw e;
		}
	}
	
	

	private static int executeUpdate(String sql, Object... params) throws SQLException {
		printSql(sql,params);
		return getQueryRunner().update(sql, params);
	}
	
	private static int[] batch(String sql,Object[][] params)throws SQLException {
		printSql(sql,params);
		return getQueryRunner().batch(sql, params);
	}
	
	private static <T> T query(String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		printSql(sql,params);
		return getQueryRunner().query(sql, rsh, params);
	}
	
	private static void printSql(String sql, Object... params){
		System.out.println("sql:" + sql );
		if(params != null && params.length != 0){
			int i = 0;
			for(Object obj : params){
				i++;
				System.out.println("params[" + i + "]" + (obj==null?"null":obj.toString()));
			}
		}
	}
	
//	private void validateParams(String sql, Object... params){
//		int i = 0;
//		int sum = 0;
//		while(true){
//			i = sql.indexOf("?", i);
//			if(i<0){
//				break;
//			}else{
//				sum++;
//				i++;
//			}
//		}
//		for(int i=0 ; i<){
//			
//		}
//	}
}
