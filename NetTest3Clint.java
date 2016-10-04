import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest3Clint {

	public static void main(String[] args) {
			try {
//				InetAddress ia = InetAddress.getByName("127.0.0.1");
//				Socket sc = new Socket(ia, 9999);
				Socket sc = new Socket("192.168.0.17", 9999);
				PrintWriter out = new PrintWriter(sc.getOutputStream(),true);//true = auto flish
				out.println("HI I'am oh" + "\n"); //서버로 전송
				
				out.close();
				sc.close();
			} catch (Exception e) {
				System.out.println(e);
			}

	}

}
