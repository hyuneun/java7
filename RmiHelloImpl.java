

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

public class RmiHelloImpl extends UnicastRemoteObject implements RmiHelloInter{
	ArrayList<String> list = new ArrayList<>();
	String ss="";
	public RmiHelloImpl() throws RemoteException{

	}
	
	@Override
	public String sayHello(String name) throws RemoteException {
		System.out.println("서버에 방문하셨군여ㅎㅎ");
		
		for (int i = 0; i < 10; i++) {
			int a = Integer.parseInt(name) * i;
			
			list.add(name + "*" + i + "=" + a);
			
			
		}
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			ss += iter.next();
			
		}
		return ss;
		
	}
}
