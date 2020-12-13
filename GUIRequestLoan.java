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

public class GUIRequestLoan {
	
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JRadioButton c1;
	private JRadioButton c2;
	private JRadioButton c3;
	private JLabel success;
	private	ButtonGroup currencyGroup;
	private JTextField loanAmountText;
	private Currency currencyType;
	private Timer timer;
	
	public GUIRequestLoan() {
		frame = new JFrame();
		
		currencyType = null; 
		timer = null;
		
		JLabel loanAmount = new JLabel("Loan Amount");
		loanAmount.setBounds(250,200,160,25);
		frame.add(loanAmount);
		
		loanAmountText = new JTextField();
		loanAmountText.setBounds(400,200,165,25);
		frame.add(loanAmountText);
		
		currencyGroup = new ButtonGroup();
		c1 = new JRadioButton("USD");
		c2 = new JRadioButton("EURO");
		c3 = new JRadioButton("YEN");
		c1.setBounds(250,250,80,25);
		c2.setBounds(350,250,80,25);
		c3.setBounds(450,250,80,25);
		currencyGroup.add(c1);
		currencyGroup.add(c2);
		currencyGroup.add(c3);
		addCurrencyGroupFunction();
		frame.add(c1);
		frame.add(c2);
		frame.add(c3);
		
		success  = new JLabel("");
		success.setBounds(250,350,300,25);
		frame.add(success);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(250,300, 80, 25);
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
					User user = Bank.getInstance().getCurrentUser();
					Customer customer = null;
					if(user instanceof Customer) {
						customer = (Customer) user;
					}
					double loanAmount = Double.parseDouble(loanAmountText.getText());
					timer = new Timer(1500, new ActionListener() {//after 1 sec, go to Customer Menu
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							GUICustomerHome customerHome = new GUICustomerHome();
							timer.stop();
							closeFrame();
						}
					});
					
					if(c1.isSelected()) {//TODO: link request loan
						currencyType = Bank.getInstance().getCurrencyTypes().get(0);
						Bank.getInstance().requestLoan(customer, currencyType, loanAmount);
						success.setText("Submit Loan Request Successful!");
						timer.start();
					} else if(c2.isSelected()) {
						currencyType = Bank.getInstance().getCurrencyTypes().get(1);
						Bank.getInstance().requestLoan(customer, currencyType, loanAmount);
						success.setText("Submit Loan Request Successful!");
						timer.start();
					} else if(c3.isSelected()) {
						currencyType = Bank.getInstance().getCurrencyTypes().get(2);
						Bank.getInstance().requestLoan(customer, currencyType, loanAmount);
						success.setText("Submit Loan Request Successful!");
						timer.start();
					} else {
						success.setText("Please choose currency type.");
					}
				} catch(Exception exception) {
					success.setText("Invalid Loan Amount");
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
