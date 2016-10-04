import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetTest4Clint {
	Socket sk;
	PrintWriter out;
	BufferedReader reader;

	public NetTest4Clint() {
		try {
			sk = new Socket("192.168.0.17", 8888);
			out = new PrintWriter(sk.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(sk.getInputStream(),"euc-kr"));
		} catch (Exception e) {
			System.out.println("생성자에러" + e);
		}
	}

	public void sendMsg() {
		try {
			//전송
			Scanner sc = new Scanner(System.in);
			System.out.println("전송자료 입력");
			String data = sc.nextLine();
			out.println(data);
			
			//서버가  넘긴자료 수신후 출력
			String re_data = reader.readLine();
			System.out.println("수신자료는" + re_data);
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				reader.close();
				out.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) {
		NetTest4Clint clint = new NetTest4Clint();
		clint.sendMsg();
	}

}
