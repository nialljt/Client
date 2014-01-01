package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class main {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

	}

}
