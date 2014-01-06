package main;

import interfaces.ReturnInterface;

import java.awt.Frame;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import chatFrame.ChatFrame;
import mainFrame.MainFrame;

public class ReturnInterfaceImpl extends UnicastRemoteObject implements
		ReturnInterface {

	protected ReturnInterfaceImpl() throws RemoteException {
		super();
	}

	@Override
	public void recieveMessage(String message, final String sender)
			throws RemoteException {
		if (sender.contains("~~ALL~~")) {
			MainFrame.messages.append(sender.substring(0, sender.indexOf("~"))
					+ ": " + message + "\n");
		} else {
			boolean found = false;
			Frame[] tst = JFrame.getFrames();
			for (Frame tmp : tst) {
				String title = tmp.getTitle();
				if (title.equals(sender)) {
					found = true;
					tmp.setVisible(true);
					ChatFrame tmpCF = (ChatFrame) tmp;
					tmpCF.messages.append(sender+": "+message+"\n");
					
					break;
				}
			}
			if (!found) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new ChatFrame(sender);
					}
				});
			}
		}

	}

	@Override
	public void updateOnlineList(Vector<String> whosOnline)
			throws RemoteException {
		MainFrame.usersOnline.clear();
		for (int i = 0; i < whosOnline.size(); i++) {
			MainFrame.usersOnline.add(whosOnline.elementAt(i));
		}
		MainFrame.online.updateUI();
	}

}
