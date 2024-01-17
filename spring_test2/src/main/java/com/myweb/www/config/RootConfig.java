package com.myweb.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@EnableScheduling
@Configuration
@MapperScan(basePackages = {"com.myweb.www.repository"})
@ComponentScan(basePackages = {"com.myweb.www.service","com.myweb.www.exception"})
public class RootConfig {
	//db에 대한 설정 부분
	//hikariCP 사용 / log4jdbc-log4j2 사용
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig =  new HikariConfig();
		//log4jdbc-log4j2
		//기본적으로 세팅하는 값
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/mywebdb");
		hikariConfig.setUsername("mywebUser");
		hikariConfig.setPassword("mysql");
		
		//hikari에서 추가되는 값
		hikariConfig.setMaximumPoolSize(5); //최대 커넥션 개수
	    hikariConfig.setMinimumIdle(5); //최소 유휴 커넥션 개수(!위 값과 같은 값으로 설정!)
	    
	    hikariConfig.setConnectionTestQuery("SELECT now()"); //처음 커넥션될 때 찍어보는것...(test)
	    hikariConfig.setPoolName("springHikariCP");
	    
	    //추가 설정,,,
	    //cachePrepStmts는 캐시 사용 여부를 설정하는 역할
	    hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
	    //mysql 드라이버가 연결당 캐시 statement의 수에 관한 설정 (250~500 사이 권장함)
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
	    //커넥션당 캐싱할 preparedStatement의 개수 지정 옵션: default 256, true =>  기본값으로 자동 지정
	    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true");
	    //안써도 상관없음, mysql 서버에서 최신 이슈가 있을 경우 지원받는 설정 => 최신 업데이트 할것인지...
	    hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
	    
	    HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
	    
	    return hikariDataSource; //DataSource return...
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml")); //mapper 경로
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return sqlFactoryBean.getObject();
	}
	
	//트랜잭션 매니저 빈 설정
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
