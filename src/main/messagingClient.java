package main;
import interfaces.MessagingInterface;
import interfaces.ReturnInterface;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import loginFrame.LoginFrame;

public class MessagingClient {
	
	public static MessagingInterface service;
	public static ReturnInterfaceImpl clientObj;
	public static String loggedinUser;
private static Scanner kb;
	
	public static void main(String[] args) {
		String registryURL = "rmi://192.168.1.73:1109/messaging"; 
		
		loggedinUser = "";
		
		kb = new Scanner(System.in);
		
		try{
			service = (MessagingInterface)Naming.lookup(registryURL);
			clientObj = new ReturnInterfaceImpl();
			service.tmpRegisterWithServer(clientObj);
			/*if(service.login("niall", "test")){
			service.unRegisterWithServerAndLogin(clientObj, false,"");
			service.registerWithServer(clientObj,true,"niall");
			}*/
			
		}catch(Exception e){
			
		}
		
		Runnable CreateAndShowGUI = new Runnable(){
			public void run(){
				new LoginFrame();
			}
		};
		SwingUtilities.invokeLater(CreateAndShowGUI);
	
		/*System.out.println("enter yes");
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
		}*/
		
	}

}
