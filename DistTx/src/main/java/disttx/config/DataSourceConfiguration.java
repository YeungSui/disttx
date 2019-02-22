package disttx.config;

import java.sql.SQLException;

import javax.sql.XADataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
public class DataSourceConfiguration {
	@Bean("druidDs")
	public DruidXADataSource createXADataSource1() throws Exception{
		DruidXADataSource ds = new DruidXADataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521/orcl");
		ds.setUsername("gguser");
		ds.setPassword("hugo");
		druidCommonConfig(ds);
		return ds;
	}
	@Bean(name="primaryDs")
	@DependsOn("druidDs")
	@Primary
	public AtomikosDataSourceBean getPds() throws Exception {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setUniqueResourceName("druidXADS1");
		ds.setXaDataSource(createXADataSource1());
		ds.setMaxPoolSize(300);
		ds.setMinPoolSize(10);
		return ds;
	}
	 @Bean(name="secondaryDs")
	 @DependsOn("druidDs2")
	 public AtomikosDataSourceBean getSdss() throws Exception {
	 	AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
	 	ds.setUniqueResourceName("druidXADS2");
	 	ds.setXaDataSource(createXADataSource2());
	 	ds.setMaxPoolSize(300);
	 	ds.setMinPoolSize(10);
	 	return ds;
	 }
	 @Bean(name="druidDs2")
	 public DruidXADataSource createXADataSource2() throws SQLException {
	 	DruidXADataSource ds = new DruidXADataSource();
	 	ds.setUsername("kpuser");
	 	ds.setPassword("hugo");
	 	ds.setDriverClassName("oracle.driver.jdbc.OracleDriver");
	 	ds.setUrl("jdbc:oracle:thin:@localhost:1521/orcl");
	 	druidCommonConfig(ds);
	 	return ds;
	 }
	private void druidCommonConfig(DruidXADataSource ds) throws SQLException {
		ds.setMaxActive(300);
		ds.setInitialSize(10);
		ds.setMinIdle(10);
		ds.setMaxWait(6000L);
		ds.setTimeBetweenEvictionRunsMillis(6000L);
		ds.setMinEvictableIdleTimeMillis(300000L);
		ds.setValidationQuery("select 1 from dual");
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(1800);
		ds.setLogAbandoned(true);
		ds.setPoolPreparedStatements(true);
		ds.setMaxPoolPreparedStatementPerConnectionSize(20);
		ds.setFilters("stat");
	}
}
