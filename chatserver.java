import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class chatserver implements Runnable {
	ServerSocket ss;
	service service;
	ArrayList<service> list = new ArrayList<>();

	public chatserver() {
		try {
			ss = new ServerSocket(5555);
			System.out.println("서비스중...");
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println("생성자 err" + e);
			System.exit(0);
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = ss.accept();
				service = new service(socket);
				service.start();
				service.chat_name = service.in.readLine();
				// System.out.println("대화명" + service.chat_name);

				list.add(service); // 모든클라이언트의 통신소켓을 컬렉션에 저장
				service.messageAll("/c" + service.chat_name);
				// for (int i = 0; i < list.size(); i++) {
				// service service = list.get(i);
				// service.messagesend("/c" + service.chat_name);
				// }

			} catch (Exception e) {
				System.out.println("run err" + e);
				return;
			}
		}

	}

	// 내부클래스 : 채팅에 참여한 각 클라이언트 처리용
	class service extends Thread {
		Socket socket;
		BufferedReader in;
		OutputStream out;
		
		String chat_name;

		public service(Socket socket) {
			try {
				this.socket = socket;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"euc-kr"));
				out = socket.getOutputStream();
			} catch (Exception e) {
				System.out.println("서비스 오류" + e);
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					String msg = in.readLine();//클라이언트자료수신
					if(msg == null || msg.equals(""))return;
					if(msg.charAt(0)== '/'){
						if(msg.charAt(1)== 'n'){
							//대화명변경 /n 새 대화명
							if(msg.charAt(2) == ' '){// /n옛이름-새이름
								messageAll("/n" + chat_name + "-" + msg.substring(3).trim());
								this.chat_name = msg.substring(3).trim();
							}
						}else if(msg.charAt(1)== 'q'){//퇴장
							try {
								messageAll("/q" + chat_name);
								list.remove(service);
								socket.close();
							} catch (Exception e) {
								
							}
							break;
						}else if(msg.charAt(1)== 's'){//귓말 /s tom-hello
							String name = msg.substring(2, msg.indexOf('-')).trim();
							for (int i = 0; i < list.size(); i++) {
								service css = (service)list.get(i);
								if(name.equals(css.chat_name)){
									css.messagesend(chat_name + ">(secret)" + msg.substring(msg.indexOf('-') + 1));
								}
							}
						
						}
					}else{
						messageAll(chat_name + ">" + msg); //일반메세지 
					}
				} catch (Exception e) {
					break;
				}
			}
		}

		public void messageAll(String msg) {
			try {
				for (int i = 0; i < list.size(); i++) {
					service ser = (service) list.get(i);
					ser.messagesend(msg);
				}
			} catch (Exception e) {
				System.out.println("all arr" + e);
			}

		}

		public void messagesend(String msg) {
			try {//
				out.write((msg + "\n").getBytes("euc-kr")); // 직렬화시킨후 클라이언트로 메세지 전송
			} catch (Exception e) {
				System.out.println("send arr" + e);
			}

		}
	}

	public static void main(String[] args) {
		new chatserver();

	}

}
