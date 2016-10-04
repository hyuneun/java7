package p1;

import java.rmi.Naming;

public class RmiHelloServer {
	
	public static void main(String[] args) throws Exception{
		//원격클래스 객체를 생성한 후 RMI Registry에 등록
		RmiHelloImpl impl = new RmiHelloImpl();
		Naming.rebind("rmi://192.168.0.34:1099/ksm", impl); 	//ksm란 이름으로 등록
		System.out.println("원격개체 바인딩 완료");
	}
}
