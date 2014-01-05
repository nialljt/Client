package main;

import interfaces.ReturnInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import mainFrame.MainFrame;

public class ReturnInterfaceImpl extends UnicastRemoteObject implements ReturnInterface {

	protected ReturnInterfaceImpl() throws RemoteException {
		super();
	}

	@Override
	public void recieveMessage(String message, String sender)
			throws RemoteException {
		System.out.println("Recived this: "+message+"From: "+sender); 
		if (sender.contains("~~ALL~~")){
		MainFrame.messages.append(sender.substring(0,sender.indexOf("~"))+": "+message+"\n");
		}else{
			
		}
		
	}

	@Override
	public void updateOnlineList(Vector<String> whosOnline)
			throws RemoteException {
		MainFrame.usersOnline.clear();
		for(int i = 0; i<whosOnline.size();i++){
			MainFrame.usersOnline.add(whosOnline.elementAt(i));
		}		
	}

}
