package disttx.generic.utils;

import disttx.config.multids.DBContext;

public class DataSourceUtils {
	public static void useDataSource(String name) {
		DBContext.set(name);
	}
}
