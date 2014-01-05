package main;
import interfaces.MessagingInterface;
import interfaces.ReturnInterface;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MessagingClient {
	
	private static MessagingInterface service;
private static Scanner kb;
	
	public static void main(String[] args) {
		String registryURL = "rmi://192.168.1.73:1109/messaging"; 

		kb = new Scanner(System.in);
		
		try{
			service = (MessagingInterface)Naming.lookup(registryURL);
			ReturnInterfaceImpl clientObj = new ReturnInterfaceImpl();
			service.registerWithServer(clientObj, false, "");
			if(service.login("niall", "test")){
			service.unRegisterWithServerAndLogin(clientObj, false,"");
			service.registerWithServer(clientObj,true,"niall");
			}
		}catch(Exception e){
			
		}
		System.out.println("enter yes");
		String answer = kb.nextLine();
		while(!answer.equalsIgnoreCase("no")){
		try {
			service.createMessage("mark","hello","niall");
			service.createMessage("mark", "how are you", "niall");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("enter yes");
		answer = kb.nextLine();
		}
		
	}

}
