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
	private JButton quitButton;
	private JButton backButton;
	private JTextField userText;
	private JTextField fNameText;
	private JTextField lNameText;
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
		
		JLabel fNameLabel = new JLabel("First Name");
		fNameLabel.setBounds(250,300,80,25);
		frame.add(fNameLabel);
		
		fNameText = new JTextField();
		fNameText.setBounds(350,300,165,25);
		frame.add(fNameText);
		
		JLabel lNameLabel = new JLabel("Last Name");
		lNameLabel.setBounds(250,350,80,25);
		frame.add(lNameLabel);
		
		lNameText = new JTextField();
		lNameText.setBounds(350,350,165,25);
		frame.add(lNameText);
		
		success  = new JLabel("");
		success.setBounds(250,420,300,25);
		frame.add(success);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(250,400, 80, 25);
		addRegisterButtonFunction();
		frame.add(registerButton);	
		

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
	
	public void addRegisterButtonFunction() {
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				timer = new Timer(1200, new ActionListener() {//after 1 sec, go back to Login
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						GUICustomerHome h = new GUICustomerHome();
						timer.stop();
						closeFrame();
					}
				});
				if(Bank.getInstance().registerNewUser(fNameText.getText(), lNameText.getText(), userText.getText(), pswText.getText(), 0, Bank.getInstance().getCurrencyTypes().get(0))) {//search database to see if unique username
					success.setText("Success!");
					timer.start();
						
				} else {
					success.setText("username already exists.");
				}
			}
			
		});
	}
	
	private void addBackButtonFunction() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUILogin in = new GUILogin(UserType.CUSTOMER);
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
