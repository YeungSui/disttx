package disttx.config.multids;

public class DBContext {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void set(String key) {
		contextHolder.set(key);
	}
	public static String get() {
		return contextHolder.get();
	}
}
