package main;

import interfaces.ReturnInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ReturnInterfaceImpl extends UnicastRemoteObject implements ReturnInterface {

	protected ReturnInterfaceImpl() throws RemoteException {
		super();
	}

	@Override
	public void recieveMessage(String message, String sender)
			throws RemoteException {
		System.out.println("Recived this: "+message+"From: "+sender); 
		
	}

}
