package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class messagingClient {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

	}

}
