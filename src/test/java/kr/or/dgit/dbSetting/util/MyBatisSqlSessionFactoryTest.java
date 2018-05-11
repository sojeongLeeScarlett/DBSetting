package kr.or.dgit.dbSetting.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;



public class MyBatisSqlSessionFactoryTest {
	private static SqlSessionFactory factory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = MyBatisSqlSessionFactory.getSqlSessionFactory(); 
	}

	@After
	public void tearDown() throws Exception {
		factory = null;
	}

	@Test
	public void test() {
		Assert.assertNotNull(factory);
	}

}
