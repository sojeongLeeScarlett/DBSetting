package kr.or.dgit.sbSetting.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import kr.or.dgit.dbSetting.dao.ExecuteSql;
import kr.or.dgit.erp_application.jdbc.DBcon;
import kr.or.dgit.erp_application.jdbc.LoadProperties;

public class BackupService implements DaoService{
	private static final BackupService instance = new BackupService();
	
	
	

	public static BackupService getInstance() {
		return instance;
	}
	
	




	public BackupService() {}






	@Override
	public void service() {
		LoadProperties loadProperties = new LoadProperties();
		Properties properties = loadProperties.getProperties();
		ExecuteSql.getInstance().execSQL("use "+properties.getProperty("dbname"));
		
		checkBackupDir();
		
		String[] tables = properties.get("tables").toString().split(",");
		for(String tblName : tables) {
			String sql = String.format("select*from %s", tblName);
			
			exportData(sql,tblName);
			
		}
		
	}






	private void exportData(String sql, String tblName) {
		try {
			ResultSet rs = ExecuteSql.getInstance().execQuery(sql);
			int columCnt = rs.getMetaData().getColumnCount();
			StringBuilder sb = new StringBuilder();
			System.out.println(sb);
			//System.out.println("columCnt"+columCnt);
			while(rs.next()) {
				for(int i=1; i<=columCnt; i++) {
					sb.append(rs.getObject(i)+",");
				}
				sb.replace(sb.length()-1, sb.length(), "");
				sb.append("\r\n");	//한줄 띄어라
			}
			writeBackupFile(sb.toString(),tblName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}






	private void writeBackupFile(String result, String tblName) {
		String resPath = System.getProperty("user.dir")+"\\BackupFiles\\"+tblName+".csv";
		resPath = resPath.replace("\\", "/");
		try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(resPath),"utf8");){
			
			osw.write(result);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}






	private void checkBackupDir() {
		File backupDir = new File(System.getProperty("user.dir")+"\\BackupFiles");
		if(backupDir.exists()) {
			for(File file:backupDir.listFiles()) {
				file.delete();
				System.out.printf("%s Delete Success! %n",file.getName());
			}
		}else {
			backupDir.mkdirs();
			System.out.printf("%s BackupFiles Directory create %n",backupDir.getName());
		}
		
	}

}
