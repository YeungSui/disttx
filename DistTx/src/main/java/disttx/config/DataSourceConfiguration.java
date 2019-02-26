package disttx.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import disttx.config.multids.DynamicDataSource;

@Configuration
public class DataSourceConfiguration {
//	@Bean(name="druidDs",initMethod="init",destroyMethod="close")
//	public DruidXADataSource createXADataSource1() throws Exception{
//		DruidXADataSource ds = new DruidXADataSource();
//		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		ds.setUrl("jdbc:oracle:thin:@localhost:1521/orcl");
//		ds.setUsername("gguser");
//		ds.setPassword("hugo");
//		druidCommonConfig(ds);
//		return ds;
//	}
//	@Bean(name="primaryDs",initMethod="init",destroyMethod="close")
//	@DependsOn("druidDs")
//	@Primary
//	public AtomikosDataSourceBean getPds() throws Exception {
//		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
//		ds.setUniqueResourceName("druidXADS1");
//		ds.setXaDataSource(createXADataSource1());
//		ds.setMaxPoolSize(300);
//		ds.setMinPoolSize(10);
//		ds.setTestQuery("select 1 from dual");
//		return ds;
//	}
//	 @Bean(name="secondaryDs",initMethod="init",destroyMethod="close")
//	 @DependsOn("druidDs2")
//	 public AtomikosDataSourceBean getSdss() throws Exception {
//	 	AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
//	 	ds.setUniqueResourceName("druidXADS2");
//	 	ds.setXaDataSource(createXADataSource2());
//	 	ds.setMaxPoolSize(300);
//	 	ds.setMinPoolSize(10);
//	 	ds.setTestQuery("select 1 from dual");
//	 	return ds;
//	 }
//	 @Bean(name="dynamicDs")
//	 public DataSource getDds(){
//		 DynamicDataSource ds = new DynamicDataSource();
//		 return ds;
//	 }
//	 @Bean(name="druidDs2",initMethod="init",destroyMethod="close")
//	 public DruidXADataSource createXADataSource2() throws SQLException {
//	 	DruidXADataSource ds = new DruidXADataSource();
//	 	ds.setUsername("kpuser");
//	 	ds.setPassword("hugo");
//	 	ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//	 	ds.setUrl("jdbc:oracle:thin:@localhost:1521/orcl");
//	 	druidCommonConfig(ds);
//	 	return ds;
//	 }
//	private void druidCommonConfig(DruidXADataSource ds) throws SQLException {
//		ds.setMaxActive(300);
//		ds.setInitialSize(10);
//		ds.setMinIdle(10);
//		ds.setMaxWait(6000L);
//		ds.setTimeBetweenEvictionRunsMillis(6000L);
//		ds.setMinEvictableIdleTimeMillis(300000L);
//		ds.setTestWhileIdle(true);
//		ds.setTestOnBorrow(false);
//		ds.setTestOnReturn(false);
//		ds.setRemoveAbandoned(true);
//		ds.setRemoveAbandonedTimeout(180);
//		ds.setLogAbandoned(true);
//		ds.setPoolPreparedStatements(true);
//		ds.setMaxPoolPreparedStatementPerConnectionSize(20);
//		ds.setValidationQuery("select 1 from dual");
//		ds.setFilters("stat");
//	}
}
