package kr.or.dgit.erp_setting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.dgit.erp_application.jdbc.DBcon;

public class ExecuteSql {
	private static final ExecuteSql instance = new ExecuteSql();
	
	public static ExecuteSql getInstance() {
		return instance;
	}
	

	private ExecuteSql() {
		
	}
	
	public void execSQL(String sql) {
		System.out.println(sql);
		Connection con = DBcon.getInstance().getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(sql);
			System.out.printf("%s-%s%n",e.getErrorCode(),e.getMessage());
		}
		
	}
	public ResultSet execQuery(String sql) throws SQLException {
		Connection con = DBcon.getInstance().getConnection();
		PreparedStatement pstmt = null;
		
			pstmt = con.prepareStatement(sql);
		
		return con.prepareStatement(sql).executeQuery();
	}
	
	
	
}
