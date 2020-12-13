import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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
	private JRadioButton c1;
	private JRadioButton c2;
	private JRadioButton c3;
	private	ButtonGroup currencyGroup;
	private Currency currencyType;
	
	public GUIRegisterUserAccount() {//TODO: link create new account. 
		frame = new JFrame();
	
		frame.setTitle("Create New Account");
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(250,100,80,25);
		frame.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(350,100,165,25);
		frame.add(userText);
		
		JLabel pswLabel = new JLabel("Password");
		pswLabel.setBounds(250,150,80,25);
		frame.add(pswLabel);
		
		pswText = new JTextField();
		pswText.setBounds(350,150,165,25);
		frame.add(pswText);
		
		JLabel fNameLabel = new JLabel("First Name");
		fNameLabel.setBounds(250,200,80,25);
		frame.add(fNameLabel);
		
		fNameText = new JTextField();
		fNameText.setBounds(350,200,165,25);
		frame.add(fNameText);
		
		JLabel lNameLabel = new JLabel("Last Name");
		lNameLabel.setBounds(250,250,80,25);
		frame.add(lNameLabel);
		
		lNameText = new JTextField();
		lNameText.setBounds(350,250,165,25);
		frame.add(lNameText);
		
		JLabel title2 = new JLabel("Choose currency type for your wallet");
		title2.setBounds(250,300,300,25);
		frame.add(title2);
		
		JLabel title3 = new JLabel("You can change it anytime after registration");
		title3.setBounds(250,320,300,25);
		frame.add(title3);
		
		currencyGroup = new ButtonGroup();
		c1 = new JRadioButton("USD");
		c2 = new JRadioButton("EURO");
		c3 = new JRadioButton("YEN");
		c1.setBounds(250,350,80,25);
		c2.setBounds(350,350,80,25);
		c3.setBounds(450,350,80,25);
		currencyGroup.add(c1);
		currencyGroup.add(c2);
		currencyGroup.add(c3);
		addCurrencyGroupFunction();
		frame.add(c1);
		frame.add(c2);
		frame.add(c3);
		
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
				
				if(c1.isSelected()) {//TODO: link request loan
					currencyType = Bank.getInstance().getCurrencyTypes().get(0);
				} else if(c2.isSelected()) {
					currencyType = Bank.getInstance().getCurrencyTypes().get(1);
				} else if(c3.isSelected()) {
					currencyType = Bank.getInstance().getCurrencyTypes().get(2);
				} else {
					success.setText("Please choose currency type.");
				}
				if(Bank.getInstance().registerNewUser(false, fNameText.getText(), lNameText.getText(), userText.getText(), pswText.getText(), 0, currencyType)) {//search database to see if unique username
					success.setText("Success!");
					timer.start();
						
				} else {
					success.setText("username already exists.");
				}
			}
			
		});
	}
	
	private void addCurrencyGroupFunction() {
		c1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c1.setSelected(true);
			}
			
		
		});
		
		c2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c2.setSelected(true);
			}
			
		
		});
		
		c3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c3.setSelected(true);
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
