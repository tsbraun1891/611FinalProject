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

public class GUIOpenNewAccount {
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JRadioButton c1;
	private JRadioButton c2;
	private JRadioButton c3;
	private JRadioButton a1;
	private JRadioButton a2;
	private JLabel success;
	private	ButtonGroup currencyGroup;
	private ButtonGroup accountTypeGroup;
	private CurrencyType currencyType;
	private AccountType accountType;
	private Timer timer;
	public GUIOpenNewAccount() {
		frame = new JFrame();
		
		currencyType = null; 
		accountType = null;
		timer = null;
		
		JLabel accountTitle = new JLabel("Choose Account Type");
		accountTitle.setBounds(250,150,160,25);
		frame.add(accountTitle);
		
		accountTypeGroup = new ButtonGroup();
		a1 = new JRadioButton("Checking");
		a2 = new JRadioButton("Saving");
		a1.setBounds(250,200,120,25);
		a2.setBounds(350,200,80,25);
		accountTypeGroup.add(a1);
		accountTypeGroup.add(a2);
		
		addAccountTypeGroupFunction();
		frame.add(a1);
		frame.add(a2);
		
		JLabel currencyTitle = new JLabel("Choose Currency Type");
		currencyTitle.setBounds(250,250,160,25);
		frame.add(currencyTitle);
		
		currencyGroup = new ButtonGroup();
		c1 = new JRadioButton("USD");
		c2 = new JRadioButton("EURO");
		c3 = new JRadioButton("YEN");
		c1.setBounds(250,300,80,25);
		c2.setBounds(350,300,80,25);
		c3.setBounds(450,300,80,25);
		currencyGroup.add(c1);
		currencyGroup.add(c2);
		currencyGroup.add(c3);
		addCurrencyGroupFunction();
		frame.add(c1);
		frame.add(c2);
		frame.add(c3);
		
		success  = new JLabel("");
		success.setBounds(250,370,300,25);
		frame.add(success);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(250,350, 80, 25);
		addSubmitButtonFunction();
		frame.add(submitButton);
		
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
	
	private void addAccountTypeGroupFunction() {
		// TODO Auto-generated method stub
		a1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				a1.setSelected(true);
			}
			
		
		});
		
		a2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				a2.setSelected(true);
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
	
	private void addSubmitButtonFunction() {
		// TODO Auto-generated method stub	
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					timer = new Timer(1500, new ActionListener() {//after 1 sec, go to Customer Menu
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							GUICustomerHome customerHome = new GUICustomerHome();
							timer.stop();
							closeFrame();
						}
					});
					
					if(c1.isSelected()) {//TODO: link 
						currencyType = CurrencyType.USD;
					} else if(c2.isSelected()) {
						currencyType = CurrencyType.EURO;
					} else if(c3.isSelected()) {
						currencyType = CurrencyType.YEN;
					} else {
						success.setText("Please choose currency type.");
					}
					
					if(a1.isSelected()) {
						accountType = AccountType.CHECKING;
					} else if(a2.isSelected()) {
						accountType = AccountType.SAVING;
					} else {
						success.setText("Please choose account type.");
					}
					
					if(currencyType != null && accountType != null) {
						success.setText("Submit Open New Account Request Successful!");
						timer.start();
					}
				} catch(Exception exception) {
					success.setText("Invalid Input");
				}
			}
			
		});
	}

	private void addBackButtonFunction() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUICustomerHome home = new GUICustomerHome();
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
