package p1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiHelloImpl extends UnicastRemoteObject implements RmiHelloInter{

	public RmiHelloImpl() throws RemoteException{

	}
	
	@Override
	public String sayHello(String name) throws RemoteException {
		System.out.println("서버에 방문하셨군여ㅎㅎ");
		return name + "님 화이팅! from ksm";
	}
}
