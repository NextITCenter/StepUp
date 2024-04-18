package common;

import java.io.IOException;
import java.io.InputStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce)  {
		// DataSource를 사용하는 코드 작성
		// 서버에서 제공하는 DataSource 사용 new를 사용하면 서버가 직접 관리하는 것이 아님 
//		BasicDataSource dataSource = new BasicDataSource();
		// 톰캣 서버의 context.xml에 JNDI 방식으로 이름을 작성한 뒤
		// 톰캣 서버가 제공하는 DataSource를 사용하는 방식으로 작성해야함
		/*
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/nextit");
			ServletContext servletContext = sce.getServletContext();
			servletContext.setAttribute("dataSource", dataSource);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		*/
		// MyBatis를 이용하도록 변경
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			ServletContext servletContext = sce.getServletContext();
			servletContext.setAttribute("sqlSession", sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

    public void contextDestroyed(ServletContextEvent sce)  {
    	
    }
}
