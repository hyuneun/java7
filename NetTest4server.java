import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetTest4server {
	ServerSocket ss;
	Socket sk;
	PrintWriter out;
	BufferedReader reader;
	
	
	public NetTest4server() {
			try {
				ss = new ServerSocket(8888);
			} catch (Exception e) {
				System.out.println("접속오류" + e);
				return;
			}
			System.out.println("서버 서비스 진행중.....");
			
			try {
				sk = ss.accept();
				out = new PrintWriter(sk.getOutputStream(),true);
				reader = new BufferedReader(new InputStreamReader(sk.getInputStream()));
				
			} catch (Exception e) {
				System.out.println("접속오류" + e);
			}
			
			
	}
	
	public void receiveMsg(){
		try {
			//서버가 수신한자료
			String msg = reader.readLine();
			System.out.println("receive data" + msg);
			
			//서버가 송신하는자료
			out.println("from server" + msg);
			reader.close(); out.close(); sk.close(); ss.close();
		} catch (Exception e) {
			System.out.println("recevr" + e);
		}
		
	}
	public static void main(String[] args) {
		while(true){
			NetTest4server sv = new NetTest4server();
			sv.receiveMsg();
		}

	}

}
