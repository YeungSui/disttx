package disttx.config.multids;

import java.util.LinkedList;
import java.util.Queue;

public class DBContext {
	private static final ThreadLocal<Queue<String>> contextHolder = new ThreadLocal<Queue<String>>();
	
	public static synchronized void add(String key) {
		Queue<String> strlist = get();
		strlist.add(key);
	}
	public static Queue<String> get() {
		return contextHolder.get();
	}
	public static synchronized void set(String key) {
		Queue<String> list = new LinkedList<String>();
		list.add(key);
		contextHolder.set(list);
	}
}
