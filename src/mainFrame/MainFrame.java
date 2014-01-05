package mainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import main.MessagingClient;


public class MainFrame extends JFrame implements ActionListener, WindowListener{

	public static Vector<String> usersOnline = new Vector<String>();
	JPanel panel = new JPanel();
	JPanel test = new JPanel();
	static DefaultListModel model = new DefaultListModel();
	
	public static JTextArea messages = new JTextArea(5, 20);
	public static JList online = new JList(usersOnline);
	JScrollPane messageScroller = new JScrollPane(messages);
	JScrollPane listScroller = new JScrollPane(online);
	public JButton send = new JButton("Send");
	public JLabel user = new JLabel("");
	private JTextField reply = new JTextField();
	private JButton chat = new JButton("Chat");
	
	public MainFrame(){
		addWindowListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Logged in as: "+MessagingClient.loggedinUser);
		
		reply.addActionListener(this);
		send.addActionListener(this);
		messages.setEditable(false);
		online.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Online"));
		messages.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Everyone"));
		
		
		add(messageScroller);
		messageScroller.setBounds(10, 10, 300, 240);
		add(listScroller);
		listScroller.setBounds(310, 10, 150, 240);
		add(reply);
		reply.setBounds(10, 260, 225, 20);
		add(send);
		send.setBounds(235, 260, 75, 20);
		add(chat);
		chat.setBounds(385, 260, 75, 20);
		setUndecorated(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setBounds((d.width - 485) / 2, (d.height - 350) / 2, 485, 350);
		setResizable(false);
		//add(panel);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send){
			if(!reply.getText().equals("")){
				try {
					MessagingClient.service.createMessage("ALL", reply.getText(), MessagingClient.loggedinUser);
					reply.setText("");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Enter some text","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == chat){
			String tmpuser = (String) online.getSelectedValue();
		}
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
				
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("here1");
		try {
			System.out.println("here2");
			MessagingClient.service.unRegisterWithServerAndLogin(MessagingClient.clientObj, true,MessagingClient.loggedinUser);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
