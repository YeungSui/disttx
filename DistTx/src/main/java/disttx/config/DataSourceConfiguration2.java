package disttx.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import disttx.config.multids.DynamicDataSource;

@Configuration
public class DataSourceConfiguration2 {
	private AtomikosDataSourceBean getPrimaryDs() {
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
	private JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter jpa = new HibernateJpaVendorAdapter();
		jpa.setDatabasePlatform("org.hibernate.dialect.OracleDialect");
		jpa.setShowSql(true);
		jpa.setGenerateDdl(false);
		return jpa;
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
	private DataSource getDynamicDataSource() {
		DynamicDataSource ds = new DynamicDataSource();
		return ds;
	}
	@Bean(name="PrimaryEmf")
	@Primary
	public EntityManagerFactory getPEmf() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setDataSource(getPrimaryDs());
		lcemfb.setPersistenceXmlLocation("classpath:persistence.xml");
		lcemfb.setPersistenceUnitName("PU");
		lcemfb.afterPropertiesSet();
		return lcemfb.getObject();
	}
	@Bean(name="secondaryEmf")
	public EntityManagerFactory getSEmf() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setDataSource(getDynamicDataSource());
		lcemfb.setPersistenceXmlLocation("classpath:persistence.xml");
		lcemfb.setPersistenceUnitName("PU");
		lcemfb.afterPropertiesSet();
		return lcemfb.getObject();
	}
}
