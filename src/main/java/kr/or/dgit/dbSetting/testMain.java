package kr.or.dgit.dbSetting;


import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;

import kr.or.dgit.erp_application.jdbc.DBcon;
import kr.or.dgit.erp_application.jdbc.LoadProperties;

public class testMain {
	public static void main(String[] args) throws SQLException, InterruptedException{
		TestDBconnection();
	}
	private static void TestDBconnection() {
		DBcon dbCon = DBcon.getInstance(); 
		System.out.println(dbCon);
		
		dbCon =  DBcon.getInstance();
		System.out.println(dbCon);
		
		LoadProperties lp = new LoadProperties();
		Properties pro = lp.getProperties();
		
		for(Entry<Object,Object> e:pro.entrySet()) {
			System.out.printf("%s : %s%n",e.getKey(),e.getValue());
		}
	
	}
}
