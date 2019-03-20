package disttx;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import disttx.config.jta.AtomikosPlatform;
import disttx.generic.entitymanager.EntityManagerMap;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ServletComponentScan
@EnableTransactionManagement
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();
		jva.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
		jva.setShowSql(true);
		jva.setGenerateDdl(false);
		return jva;
	}
	@Bean(name="userTransaction")
	public UserTransaction userTransaction() throws Throwable{
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(300000);
		return userTransactionImp;
	}
	@Bean(name="atomikosTransactionManager", initMethod="init", destroyMethod="close")
	public TransactionManager atomikosTransactionManager() {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		AtomikosPlatform.transactionManager = userTransactionManager;
		return userTransactionManager;
	}
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("atomikosTransactionManager")TransactionManager tm, 
			@Qualifier("userTransaction")UserTransaction ut) throws Throwable{
		return new JtaTransactionManager(ut,tm);
	}
	@Bean(name="entityManagerMap")
	public EntityManagerMap getEmMap() {
		return new EntityManagerMap();
	}
}
