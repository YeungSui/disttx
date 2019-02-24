package connectionpooltest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnThread extends Thread {
	@Override
	public void run() {
		try {
			Socket soc = new Socket("127.0.0.1",8080);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));
			pw.println("GET /test HTTP/1.1");
			pw.println("Host: localhost");
			pw.println("Content-Type: text/html");
			pw.println();
			pw.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String str = "";
			while((str = br.readLine()) != null) {
				System.out.println("Thread-"+Thread.currentThread().getId()+" result: "+str);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
