

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface RmiHelloInter extends Remote{
	public String sayHello(String name) throws RemoteException;
}
