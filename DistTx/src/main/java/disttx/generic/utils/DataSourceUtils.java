package disttx.generic.utils;

import disttx.config.multids.DBContext;

public class DataSourceUtils {
	public static void useDataSource(String name) {
		useDataSource(name,false);
	}
	public static void useDataSource(String name, boolean start) {
		if(!start) {
			DBContext.add(name);
		} else {
			DBContext.set(name);
		}
		
	}
}
