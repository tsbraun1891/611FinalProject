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
 * This class represents the window that allows customer to send money to other customers. 
 * Customer will send money from a specific account and enter recipient's username. 
 * If successfully transferred money to the recipient, the moeny will goes to recipient's wallet(balance).
 * @author ling
 *
 */
public class GUITransferTo {
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JLabel success;
	private JComboBox combo;
	private JTextField transferAmountText;
	private JTextField recipientText;
	
	public GUITransferTo() {
		frame  = new JFrame();
		
		JLabel transferAmount = new JLabel("Transfer Amount");
		transferAmount.setBounds(250,200,160,25);
		frame.add(transferAmount);
		
		transferAmountText = new JTextField();
		transferAmountText.setBounds(400,200,165,25);
		frame.add(transferAmountText);
		
		success  = new JLabel("");
		success.setBounds(250,390,400,25);
		frame.add(success);
		
		JLabel withdrawalFrom = new JLabel("Withdrawal From");
		withdrawalFrom.setBounds(250,250,160,25);
		frame.add(withdrawalFrom);
		
		DefaultComboBoxModel accounts = new DefaultComboBoxModel();
		for(Account account: Bank.getInstance().getCurrentUser().getAccounts()) {
			accounts.addElement(account.toString());
		}
		
		combo = new JComboBox(accounts);
		
		
		JScrollPane accountPane= new JScrollPane(combo);
		accountPane.setBounds(400, 250,170, 45);
		frame.add(accountPane);
		
		JLabel transferTo = new JLabel("Send Money To");
		transferTo.setBounds(250,300,160,25);
		frame.add(transferTo);
		
		recipientText = new JTextField();
		recipientText.setBounds(400,300,165,25);
		frame.add(recipientText);
		
		JLabel hint = new JLabel("Please provide the username of the recipient");
		hint.setBounds(250,320,300,25);
		frame.add(hint);
		
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(250,370, 80, 25);
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
					double amount = Double.parseDouble(transferAmountText.getText());
					User recipient = Bank.getInstance().getUserByUsername(recipientText.getText());
		            if ((combo.getSelectedIndex() != -1) && amount > 0 && recipient != null) {                         
		            	if(Bank.getInstance().transferMoney(Bank.getInstance().getCurrentUser().getAccounts().get(combo.getSelectedIndex()), recipient , amount))
		            		success.setText("Transfer Success!");
		            	else 
		            		success.setText("No enough money in this account");
		            } else if((combo.getSelectedIndex() == -1)) {
		            	success.setText("You don't have an account yet. Please open a new account.");
					} else {
		            	success.setText("Please provide valid info");
		            }
				} catch(Exception exception) {
					success.setText("Invalid Deposit Amount");
				}
			}
			
		});
	}

	private void addBackButtonFunction() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUITransfer t = new GUITransfer();
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
