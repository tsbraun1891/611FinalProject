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

public class GUITransferBetween {
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JLabel success;
	private JComboBox combo;
	private JComboBox combo2;
	private JTextField transferAmountText;
	
	
	public GUITransferBetween() {
		frame = new JFrame();	
		
		JLabel transferAmount = new JLabel("Transfer Amount");
		transferAmount.setBounds(250,200,160,25);
		frame.add(transferAmount);
		
		transferAmountText = new JTextField();
		transferAmountText.setBounds(400,200,165,25);
		frame.add(transferAmountText);
		
		success  = new JLabel("");
		success.setBounds(250,375,300,25);
		frame.add(success);
		
		JLabel withdrawalFrom = new JLabel("Transfer From");//++wallet
		withdrawalFrom.setBounds(250,250,160,25);
		frame.add(withdrawalFrom);
		
		DefaultComboBoxModel accounts = new DefaultComboBoxModel();
		accounts.addElement("Your wallet");
		for(Account account: Bank.getInstance().getCurrentUser().getAccounts()) {
			accounts.addElement(account.toString());
		}
		
		combo = new JComboBox(accounts);
		
		JScrollPane accountPane= new JScrollPane(combo);
		accountPane.setBounds(400, 250,170, 45);
		frame.add(accountPane);
		
		JLabel transferTo = new JLabel("Transfer To");
		transferTo.setBounds(250,300,160,25);
		frame.add(transferTo);
		
		DefaultComboBoxModel accounts2 = new DefaultComboBoxModel();
		accounts2.addElement("Your wallet");
		for(Account account: Bank.getInstance().getCurrentUser().getAccounts()) {
			accounts2.addElement(account.toString());
		}
		combo2 = new JComboBox(accounts2);
		
		JScrollPane accountPane2 = new JScrollPane(combo2);
		accountPane2.setBounds(400,300,170,45);
		frame.add(accountPane2);
		
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
	
	private void addSubmitButtonFunction() {
		// TODO Auto-generated method stub	
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try { 
					double amount = Double.parseDouble(transferAmountText.getText());
					Account sender = null;
					Account receiver = null;
					
					if ((combo.getSelectedIndex() != -1)) {//choose sender
						if(combo.getSelectedIndex() != 0) {
							sender = Bank.getInstance().getCurrentUser().getAccounts().get(combo.getSelectedIndex()-1);	
						} 
					} else {
						success.setText("Choose an account/wallet");
					}
					
					if ((combo2.getSelectedIndex() != -1)) {//choose receiver
						if(combo2.getSelectedIndex() != 0) {
							receiver = Bank.getInstance().getCurrentUser().getAccounts().get(combo2.getSelectedIndex()-1);	
						}
					} else {
						success.setText("Choose an account/wallet");
					}
					
					if((sender == null && receiver == null)) {
						success.setText("You can't transfer to same account.");
					} else if(sender != null && receiver != null) {
						if(sender.equals(receiver))
							success.setText("You can't transfer to same account.");	
					} else {
						User user = Bank.getInstance().getCurrentUser();
						if(Bank.getInstance().transferBetweenAccount(user, sender, receiver, amount)) {
							success.setText("Transfer Between Account Success!");
						} else {
							success.setText("Please enter a valid amount of money");
						}
					}
					
				} catch(Exception exception) {
					success.setText("Invalid Transfer Amount");
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
