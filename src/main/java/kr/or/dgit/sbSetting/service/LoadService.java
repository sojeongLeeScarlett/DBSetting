package kr.or.dgit.sbSetting.service;

import java.io.File;
import java.util.Properties;

import kr.or.dgit.dbSetting.dao.ExecuteSql;
import kr.or.dgit.dbSetting.jdbc.DBcon;
import kr.or.dgit.dbSetting.jdbc.LoadProperties;

public class LoadService implements DaoService {
	private static final LoadService instance = new LoadService();
	
	
	

	public static LoadService getInstance() {
		return instance;
	}





	public LoadService() {}
	




	@Override
	public void service() {
		
		LoadProperties loadProperties = new LoadProperties();
		Properties properties = loadProperties.getProperties();
		
		ExecuteSql.getInstance().execSQL("use " + properties.getProperty("dbname"));
		ExecuteSql.getInstance().execSQL("set foreign_key_checks=0");
	
		String[] tables = properties.get("tables").toString().split(",");
		for(String tblName : tables) {
			String path = String.format("%s\\DataFiles\\%s.csv", System.getProperty("user.dir"),tblName);
			String sql = String.format("load data local infile '%s' into table %s character set 'utf8' fields terminated by ','",path,tblName);
			
			sql = sql.replace("\\", "/");
			ExecuteSql.getInstance().execSQL(sql);
			
		}
		ExecuteSql.getInstance().execSQL("set foreign_key_checks=1");
	
	}

}
