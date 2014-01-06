package chatFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.MessagingClient;

public class ChatFrame extends JFrame implements ActionListener{
	
	public String chattingTo = "";
	
	public static JTextArea messages = new JTextArea(5, 20);
	JScrollPane messageScroller = new JScrollPane(messages);
	public JButton send = new JButton("Send");
	private JTextField reply = new JTextField();
	public ChatFrame(String user){
		this.chattingTo = user;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		send.addActionListener(this);
		messages.setEditable(false);
		setLayout(null);
		setTitle(chattingTo);
		add(messageScroller);
		messageScroller.setBounds(10, 10, 300, 240);
		add(reply);
		reply.setBounds(10, 260, 225, 20);
		add(send);
		send.setBounds(235, 260, 75, 20);
		setUndecorated(false);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setBounds((d.width - 350) / 2, (d.height - 300) / 2, 350, 325);
		setResizable(false);
		//add(panel);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == send){
			if(!reply.getText().equals("")){
				try {
					MessagingClient.service.createMessage(chattingTo, reply.getText(), MessagingClient.loggedinUser);
					messages.append("ME: "+reply.getText()+"\n");
					reply.setText("");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Enter some text","Error",JOptionPane.ERROR_MESSAGE);
			}	
	}
	}
}
