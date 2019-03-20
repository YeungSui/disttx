package disttx.config;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import disttx.config.jta.AtomikosPlatform;
import disttx.config.multids.DynamicDataSource;

@Configuration
@DependsOn("transactionManager")
public class DataSourceConfiguration2 {
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;
	
	@Bean(name="primary", initMethod="init", destroyMethod="close")
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
		props.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		props.put("hibernate.transaction.jta.platform", AtomikosPlatform.class.getName());
		props.put("javax.persistence.transactionType", "JTA");
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setJpaVendorAdapter(jpaVendorAdapter);
		em.setJpaProperties(props);
		em.setJtaDataSource(ds);
		em.setPackagesToScan("disttx.player");
		em.setPersistenceUnitName("primaryPU");
		return em;
	}
	@Bean(name="secondaryEmf")
	public LocalContainerEntityManagerFactoryBean getSEmf(EntityManagerFactoryBuilder builder, @Qualifier("secondary")DataSource ds) {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		props.put("hibernate.transaction.jta.platform", AtomikosPlatform.class.getName());
		props.put("javax.persistence.transactionType", "JTA");
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setJpaVendorAdapter(jpaVendorAdapter);
		em.setPersistenceUnitName("secondaryPU");
		em.setJtaDataSource(ds);
		em.setPackagesToScan("disttx.syslog","disttx.club");
		em.setJpaProperties(props);
		return em;
	}
}
