

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest2 {

	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("www.daum.net");
			Socket sc = new Socket(ia, 80);
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
			out.println("GET http://www.daum.net");// 서버로요청
			out.flush();// 요청후 버퍼를 지움

			// 서버에서 넘어온 자료를 출력
			BufferedReader rd = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			while(true){
				String str = rd.readLine();
				if(str == null) break;
				System.out.println(str);
			}
			rd.close(); out.close();  sc.close();
		} catch (Exception e) {

		}

	}

}
