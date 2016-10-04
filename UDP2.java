import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP2 {

	public static void main(String[] args) {
		try {
			DatagramSocket sc = new DatagramSocket(7777);
			byte[] data = new byte[65535];
			
			DatagramPacket pc = new DatagramPacket(data, data.length);
			System.out.println("서비스 시작....");
			
			while(true){
				sc.receive(pc);
				System.out.println("보낸곳 주소 : " + pc.getAddress().getHostAddress());
				System.out.println("보낸곳 이름 : " + pc.getAddress().getHostName());
				System.out.println("자료크기 : " + pc.getLength());
				System.out.println("자료내용 : " + new String(pc.getData()).trim());
			}
		} catch (Exception e) {
			System.out.println("수신에러");
		}

	}

}
