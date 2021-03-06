import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * This class represents the window that allows customer to deposit from ATM to a specific account. 
 * If successfully deposit, the money will be subtracted from customer's wallet(balance) to their chosen account.
 * @author ling
 *
 */

public class GUIDeposit {
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JLabel success;
	private JComboBox combo;
	private JTextField depositAmountText;
	
	public GUIDeposit() {
		frame = new JFrame();
		
		JLabel depositAmount = new JLabel("Deposit Amount");
		depositAmount.setBounds(250,200,160,25);
		frame.add(depositAmount);
		
		depositAmountText = new JTextField();
		depositAmountText.setBounds(400,200,165,25);
		frame.add(depositAmountText);
		
		success  = new JLabel("");
		success.setBounds(250,350,300,25);
		frame.add(success);
		
		JLabel depositTo = new JLabel("Deposit To");
		depositTo.setBounds(250,250,160,25);
		frame.add(depositTo);
		
		DefaultComboBoxModel accounts = new DefaultComboBoxModel();
		for(Account account: Bank.getInstance().getCurrentUser().getAccounts()) {
			accounts.addElement(account.toString());
		}	
		combo = new JComboBox(accounts);
		
		JScrollPane accountPane= new JScrollPane(combo);
		accountPane.setBounds(400, 250,170, 45);
		frame.add(accountPane);
		
		
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
	
	
	private void addSubmitButtonFunction() {
		// TODO Auto-generated method stub	
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					double amount = Double.parseDouble(depositAmountText.getText());
					if ((combo.getSelectedIndex() != -1)) {
						Account chosen = Bank.getInstance().getCurrentUser().getAccounts().get(combo.getSelectedIndex());
						if(Bank.getInstance().depositToAccount(Bank.getInstance().getCurrentUser(), chosen, amount)) {
							success.setText("Deposit Success!");
						} else {
							success.setText("Please enter a valid amount of money");
						}
					} else {
						success.setText("Choose an account/wallet");
					}
				} catch(Exception exception) {
					success.setText("Invalid Deposit Amount");
					//exception.printStackTrace();

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
