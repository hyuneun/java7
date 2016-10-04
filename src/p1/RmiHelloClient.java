package p1;

import java.rmi.Naming;

public class RmiHelloClient {
	
	public static void main(String[] args) {
		try {
			RmiHelloInter h = (RmiHelloInter)Naming.lookup("rmi://192.168.0.17:1099/ksm");
			String result = h.sayHello("홍길동");	//RMI
			System.out.println("RMI 수행 결과 : " + result);
			
		} catch (Exception e) {
			System.out.println("client err : " + e);
		}
	}
}
