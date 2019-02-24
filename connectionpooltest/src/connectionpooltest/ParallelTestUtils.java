package connectionpooltest;

public class ParallelTestUtils {
	public static void parallelConn() {
		for(int i = 0; i < 10; i++) {
			ConnThread conn = new ConnThread();
			conn.start();
		}
	}
}
