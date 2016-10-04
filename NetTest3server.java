import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class NetTest3server {

	public static void main(String[] args) {
		ServerSocket ss = null;

//		for (int i = 0; i <= 65536; i++) { //내컴퓨터의 사용중인 포트번호확인
//			try {
//				ss = new ServerSocket(i);
//				ss.close();
//			} catch (Exception e) {
//				System.out.println(i + "번 포트는 사용중입니다");
//			}
//		}
		
		Socket sk = null;
		try {
			ss = new ServerSocket(9999);
			System.out.println("서비스중");
			sk = ss.accept();
			System.out.println("접속자정보" + sk.toString());
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(sk.getInputStream()));
			String data = reader.readLine();
			System.out.println("수진받은자료" + data);
			
			sk.close(); ss.close(); reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
