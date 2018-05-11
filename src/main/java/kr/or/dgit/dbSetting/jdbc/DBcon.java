package kr.or.dgit.dbSetting.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class DBcon {
	private static final DBcon instance = new DBcon();
	private Connection connection;
	
	

	public static DBcon getInstance() {
		return instance;
	}

	private DBcon() {
		//보통 쓰는것
		//String url = "jdbc:mysql://localhost:3306/erp_project";
	/*	String user = "root";
		String password = "rootroot";*/
		try {
			//connection = DriverManager.getConnection(url, user, password);
			//마이바티스에서 씀
			LoadProperties pro = new LoadProperties();
			Properties info = pro.getProperties();
			connection = DriverManager.getConnection(info.getProperty("url"), info);
		} catch (SQLException e) {
			System.err.printf("%s= %s%n",e.getMessage(),e.getErrorCode());
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void connectionClose() {
		try {
			connection.close();
			JOptionPane.showMessageDialog(null, "연결이 해제되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
