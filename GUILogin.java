import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GUILogin {
	private UserType userType;
	private Timer timer = null;
	private JFrame frame;
	private JLabel success;
	private JButton loginButton;
	private JButton registerButton;
	private JTextField userText;
	private JTextField pswText;

	public GUILogin(UserType userType) {
		// TODO Auto-generated constructor stub
		this.userType = userType;
		frame = new JFrame();
		
		JLabel userLabel = new JLabel("User");
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
		
		loginButton = new JButton("Login");
		loginButton.setBounds(250,300, 80, 25);
		addLoginButtonFunction();
		frame.add(loginButton);	
		
		addRegisterButton();
		
		addBackgroundPic();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}
	
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("bg.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
	}
	
	public void addLoginButtonFunction() {
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = userText.getText();
				String password = pswText.getText();
				//TODO: search database to see if userName and password match any record.
				if(userType.equals(UserType.CUSTOMER)) {//search customer
					timer = new Timer(1000, new ActionListener() {//after 1 sec, go to Customer Menu
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							GUICustomerHome customerHome = new GUICustomerHome();
							timer.stop();
							closeFrame();
						}
					});
					if(userName.equals("Ling") && password.equals("abc")) {
						success.setText("Customer Login Successful!");
						timer.start();
						
					} else {
						success.setText("Invalid username or password.");
					}
				} else if(userType.equals(UserType.ADMIN)) { //search manager
					timer = new Timer(1000, new ActionListener() {//after 1 sec, go to Customer Menu
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							GUIAdminHome adminHome = new GUIAdminHome();
							timer.stop();
							closeFrame();
						}
					});
					if(userName.equals("Manager") && password.equals("abc")) {
						success.setText("Admin Login Successful!");
						timer.start();
					} else {
						success.setText("Invalid username or password.");
					}
				}
			}
			
		});
	}
	
	public void addRegisterButton() {
		if(userType.equals(UserType.CUSTOMER)) {
			registerButton = new JButton("Register");
			registerButton.setBounds(400,300, 80, 25);
			registerButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					GUIRegisterUserAccount register = new GUIRegisterUserAccount();
					closeFrame();
				}
				
			});
			frame.add(registerButton);	
		}
	}
	
	public void closeFrame() {
		frame.dispose();
	}
}
