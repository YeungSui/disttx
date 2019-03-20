package disttx.config;

import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
public class JtaConfiguration {
//	@Bean(name="xatx")
//	public JtaTransactionManager regTx() {
//		UserTransactionManager utxm = new UserTransactionManager();
//		UserTransaction utx = new UserTransactionImp();
//		return new JtaTransactionManager(utx, utxm);
//	}
}
