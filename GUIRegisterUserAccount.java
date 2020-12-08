import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GUIRegisterUserAccount {
	
	private JFrame frame;
	private Timer timer = null;
	private JLabel success;
	private JButton registerButton;
	private JTextField userText;
	private JTextField pswText;
	
	public GUIRegisterUserAccount() {//TODO: link create new account. 
		frame = new JFrame();
		
		JLabel title = new JLabel("Create New Account");
		title.setBounds(250, 150, 160, 25);
		frame.add(title);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(250,200,80,25);
		frame.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(350,200,165,25);
		frame.add(userText);
		
		
		JLabel pswLabel = new JLabel("Password");
		pswLabel.setBounds(250,250,80,25);
		frame.add(pswLabel);
		
		pswText = new JTextField();
		pswText.setBounds(350,250,165,25);
		frame.add(pswText);
		
		success  = new JLabel("");
		success.setBounds(250,350,300,25);
		frame.add(success);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(250,300, 80, 25);
		addRegisterButtonFunction();
		frame.add(registerButton);	
		
		addBackgroundPic();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void addRegisterButtonFunction() {
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = userText.getText();
				String password = pswText.getText();
				timer = new Timer(1200, new ActionListener() {//after 1 sec, go back to Login
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						GUILogin login = new GUILogin(UserType.CUSTOMER);
						timer.stop();
						closeFrame();
					}
				});
				if(userName.equals("new") && password.equals("abc")) {//TODO: search database to see if unique username
					success.setText("Success! Transfer back to login page...");
					timer.start();
						
				} else {
					success.setText("username already exists.");
				}
			}
			
		});
	}
	
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("image/bg.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
	}
	
	public void closeFrame() {
		frame.dispose();
	}
}
