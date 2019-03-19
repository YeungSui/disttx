package disttx.config;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import disttx.config.multids.DynamicDataSource;

@Configuration
public class DataSourceConfiguration2 {
	@Bean("primary")
	@Primary
	public AtomikosDataSourceBean getPrimaryDs() {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setUniqueResourceName("orclXADS1");
		ds.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
		Properties props = new Properties();
		props.put("user","gguser");
		props.put("password","hugo");
		props.put("URL","jdbc:oracle:thin:@localhost:1521/orcl");
		ds.setXaProperties(props);
		ds.setMinPoolSize(10);
		ds.setMaxPoolSize(50);
		ds.setTestQuery("select 1 from dual");
		return ds;
	}
//	public AtomikosDataSourceBean getSds() {
//		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
//		ds.setUniqueResourceName("orclXADS2");
//		ds.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
//		Properties props = new Properties();
//		props.put("user","kpuser");
//		props.put("password","hugo");
//		props.put("URL","jdbc:oracle:thin:@localhost:1521/orcl");
//		ds.setXaProperties(props);
//		ds.setMinPoolSize(10);
//		ds.setMaxPoolSize(50);
//		ds.setTestQuery("select 1 from dual");
//		return ds;
//	}
	@Bean("secondary")
	public DataSource getDynamicDataSource(@Qualifier("primary")DataSource pds) {
		DynamicDataSource ds = new DynamicDataSource();
		ds.setTargetDataSources(new HashMap());
		ds.setDefaultTargetDataSource(pds);
		return ds;
	}
	@Bean(name="primaryEmf")
	@Primary
	public LocalContainerEntityManagerFactoryBean getPEmf(EntityManagerFactoryBuilder builder, @Qualifier("primary")DataSource ds) {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		LocalContainerEntityManagerFactoryBean emf = builder
				.dataSource(ds).packages("disttx.player")
				.persistenceUnit("primaryPU")
				.jta(true)
				.build();
		emf.setJpaProperties(props);
		return emf;
	}
	@Bean(name="secondaryEmf")
	public LocalContainerEntityManagerFactoryBean getSEmf(EntityManagerFactoryBuilder builder, @Qualifier("secondary")DataSource ds) {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		LocalContainerEntityManagerFactoryBean emf = builder
				.dataSource(ds)
				.packages("disttx.syslog")
				.persistenceUnit("secondaryPU")
				.jta(true)
				.build();
		emf.setJpaProperties(props);
		return emf;
	}
}
