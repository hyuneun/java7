

import java.rmi.Naming;

public class RmiHelloClient {
	
	public static void main(String[] args) {
		try {
			RmiHelloInter h = (RmiHelloInter)Naming.lookup("rmi://192.168.0.41:1099/ksm");
			String result = h.sayHello("2");	//RMI
			System.out.println("RMI 수행 결과 : " + result);
			
		} catch (Exception e) {
			System.out.println("client err : " + e);
		}
	}
}
