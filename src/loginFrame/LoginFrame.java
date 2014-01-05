package loginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import main.MessagingClient;
import main.ReturnInterfaceImpl;
import mainFrame.MainFrame;

public class LoginFrame extends JFrame implements ActionListener {

	JPanel panel = new JPanel();
	JTextField loginField = new JTextField(13);
	JPasswordField passwordField = new JPasswordField(13);
	JButton loginButton = new JButton("Login");
	JButton cancelButton = new JButton("Cancel");
	JLabel blankLabel = new JLabel("   ");
	
	public LoginFrame(){
		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);
		loginField.setBorder(BorderFactory.createTitledBorder("User Name"));
		passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
		panel.setLayout(new GridBagLayout());
		panel.add(loginField,getConstraints(0,0,5,1, GridBagConstraints.CENTER,0,0,0,0));
		panel.add(passwordField,getConstraints(0,1,5,1, GridBagConstraints.CENTER,0,0,0,0));
		panel.add(blankLabel,getConstraints(0,2,5,2, GridBagConstraints.CENTER,0,0,0,0));
		panel.add(loginButton,getConstraints(4,4,1,1, GridBagConstraints.EAST,0,0,0,0));
		panel.add(cancelButton,getConstraints(0,4,1,1, GridBagConstraints.CENTER,0,0,0,0));
		setUndecorated(true);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setBounds((d.width - 200) / 2, (d.height - 140) / 2, 245, 150);
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		add(panel);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton){
			try {
				if(MessagingClient.service.login(loginField.getText(), passwordField.getText())){
					MessagingClient.service.unRegisterWithServerAndLogin(MessagingClient.clientObj, false,"");
					MessagingClient.service.registerWithServer(MessagingClient.clientObj,true,"niall");
					dispose();
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							new MainFrame();
						}
					});
				}else{
					JOptionPane.showMessageDialog(null, "Check Username/Password","Error",JOptionPane.ERROR_MESSAGE);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == cancelButton){
			try {
				MessagingClient.service.unRegisterWithServerAndLogin(MessagingClient.clientObj, false,"");
			} catch (RemoteException e1) {
				System.exit(EXIT_ON_CLOSE);
			}
			System.exit(EXIT_ON_CLOSE);
		}
		
		
	}
	
	private GridBagConstraints getConstraints(int gridx, int gridy,
			int gridwidth, int gridheight, int anchor, int inset1, int inset2,
			int inset3, int inset4) {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(inset1, inset2, inset3, inset4);
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		c.anchor = anchor;
		return c;
	}

}
