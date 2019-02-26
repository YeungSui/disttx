package disttx.config.multids;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.atomikos.jdbc.AtomikosDataSourceBean;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private final String DEFAULT_DATABASE = "datasource";
	private Map dsMap = new HashMap<String, DataSource>();
	private final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/orcl";
	
	@Override
	protected Object determineCurrentLookupKey() {
		String dsName = DBContext.get();
		selectDataSource(dsName);
		return dsName;
	}
	private void selectDataSource(String dsName) {
		if(!"barhbe".equals(dsName) && !"barhbe".equals(dsName)) {
			return;
		}
		if("goodgoods".equals(dsName) && dsMap.get("goodgoods") == null) {
			dsMap.put(dsName,initBarhbe());
		}
		else if("kwanpaang".equals(dsName) && dsMap.get("kwanpaang") == null) {
			dsMap.put(dsName,initKwanPaang());
		}
		this.setTargetDataSources(dsMap);
		this.afterPropertiesSet();
	}
	private DataSource initBarhbe() {
		AtomikosDataSourceBean ds1 = new AtomikosDataSourceBean();
		ds1.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
		Properties props = new Properties();
		props.put("username","bhbuser");
		props.put("password","hugo");
		props.put("url",jdbcUrl);
		ds1.setXaProperties(props);
		ds1.setMaxPoolSize(100);
		ds1.setMinPoolSize(10);
		ds1.setUniqueResourceName("dynamicDs1");
		return ds1;
	}
	private DataSource initKwanPaang() {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		ds.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
		Properties props = new Properties();
		props.put("username", "kpuser");
		props.put("password","hugo");
		props.put("url",jdbcUrl);
		ds.setXaProperties(props);
		ds.setMaxPoolSize(100);
		ds.setMinPoolSize(10);
		return ds;
	}
}
