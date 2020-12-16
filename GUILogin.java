import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
/**
 * This class represents the window that allows both userTypes: customer, admin to log in.
 * @author ling
 *
 */
public class GUILogin {
	private UserType userType;
	private Timer timer;
	private JFrame frame;
	private JLabel success;
	private JButton loginButton;
	private JButton registerButton;
	private JTextField userText;
	private JTextField pswText;
	private JButton backButton;
	private JButton quitButton;

	public GUILogin(UserType userType) {
		// TODO Auto-generated constructor stub
		this.userType = userType;
		timer = null;
		frame = new JFrame();
		
		if(userType.equals(UserType.ADMIN)) {
			frame.setTitle("Admin Login");
		} else if (userType.equals(UserType.CUSTOMER)) {
			frame.setTitle("Customer Login");
		}
		
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
		
		loginButton = new JButton("Login");
		loginButton.setBounds(250,300, 80, 25);
		addLoginButtonFunction();
		frame.add(loginButton);	
		
		addRegisterButton();
		
		backButton = new JButton("Back");
		backButton.setBounds(10, 500, 80, 25);
		addBackButtonFunction();
		frame.add(backButton);
		
		quitButton = new JButton("Quit");
		quitButton.setBounds(710, 500, 80, 25);
		addQuitButtonFunction();
		frame.add(quitButton);
		
		addBackgroundPic();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
	
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("image/bg.jpg");
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
					if(Bank.getInstance().login(userName, password)) {
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
					if(Bank.getInstance().login(userName, password)) {
						if(Bank.getInstance().getAdmin().getUserName().equals(userName) && Bank.getInstance().getAdmin().getPassword().equals(password)) {
							success.setText("Admin Login Successful!");
							timer.start();
						} else {
							success.setText("You don't have Admin access");
						}
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
	
	private void addBackButtonFunction() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIChooseUserType type = new GUIChooseUserType();
				closeFrame();
			}		
		});
	}
	
	private void addQuitButtonFunction() {
		// TODO Auto-generated method stub
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIQuit quit = new GUIQuit();
				closeFrame();
			}		
		});
	}
	
	public void closeFrame() {
		frame.dispose();
	}
}
