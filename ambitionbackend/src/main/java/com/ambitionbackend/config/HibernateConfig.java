package com.ambitionbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages={"com.ambitionbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	
	// Change the below based on the DBMS you choose
		private final static String DATABASE_URL="jdbc:h2:tcp://localhost/~/ambition";
		private final static String DATABASE_DRIVER="org.h2.Driver";
		private final static String DATABASE_DIALECT="org.hibernate.dialect.H2Dialect";
		private final static String DATABASE_USERNAME="sa";
		private final static String DATABASE_PASSWORD="";
		
		
		// dataSource bean will be available
		@Bean("datasource")
		public DataSource getDataSource()
		{
			BasicDataSource dataSource = new BasicDataSource();
			// Providing the database connection information
			dataSource.setDriverClassName(DATABASE_DRIVER);
			dataSource.setUrl(DATABASE_URL);
			dataSource.setUsername(DATABASE_USERNAME);
			dataSource.setPassword(DATABASE_PASSWORD);
		
			return dataSource;
			
		}
		
		// sessionFactory bean will be available
		@Bean
		public SessionFactory getSessionFactory(DataSource dataSource){
			LocalSessionFactoryBuilder  builder= new LocalSessionFactoryBuilder( dataSource);
			builder.addProperties(getHibarnateProperties(""));
			builder.scanPackages("com.ambitionbackend.dto");
			return builder.buildSessionFactory();
			
		}
		// all the hibarnate  Property will return this
	private Properties getHibarnateProperties(String string) {
		    Properties properties= new Properties();
		    properties.put("hibarnate.dialect", DATABASE_DIALECT);
		    properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.format_sql", "true");
		
			//properties.put("hibernate.hbm2ddl.auto", "create");
			return properties;
		
		}
	
	
		
		@Bean
		public HibernateTransactionManager getHibernateTransactionManager(SessionFactory factory){
			HibernateTransactionManager hibernateTransactionManager= new HibernateTransactionManager(factory);
			return hibernateTransactionManager;
			
		}

}
