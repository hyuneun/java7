import java.net.InetAddress;

public class NetworkTest {

	public static void main(String[] args) {
		InetAddress adr = null;//ip주소를 얻기위한 클래스
		InetAddress[] arr = null;
		try {
			adr = InetAddress.getByName("www.daum.net");
			System.out.println(adr);
			System.out.println(adr.getHostAddress());
			System.out.println(adr.getHostName());
			System.out.println("____");
			
			adr = InetAddress.getLocalHost();
			System.out.println(adr);
			System.out.println(adr.getHostAddress());
			System.out.println(adr.getHostName());
			System.out.println("____");
			
			arr = InetAddress.getAllByName("www.naver.com");
			System.out.println(arr);
			System.out.println(arr.length);
			
			for(InetAddress aa:arr){
				System.out.println(aa.getHostAddress());
				System.out.println(aa.getHostName());
			}
				
			
		} catch (Exception e) {
			System.out.println("network err" + e);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
