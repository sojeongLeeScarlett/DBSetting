package kr.or.dgit.sbSetting.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import kr.or.dgit.dbSetting.dao.ExecuteSql;

public class InitService implements DaoService {
	private static final InitService instance = new InitService();
	
	public static InitService getInstance() {
		return instance;
	}
	
	private InitService() {}
	

	@Override
	public void service() {
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\sql\\create_sql.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(f));) {
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine())!=null) {
				if(!line.isEmpty() && !line.startsWith("--")) {
					sb.append(line);
				}
				if(line.endsWith(";")) {
					//System.out.println(sb);
					ExecuteSql.getInstance().execSQL(sb.toString());
					sb.setLength(0);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e1) {
			e1.printStackTrace();
		}
	

	}

}
