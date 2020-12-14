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

public class GUIPayOffLoan {
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JLabel success;
	private JLabel success2;
	private JComboBox combo;
	private JComboBox combo2;
	private JTextField transferAmountText;
	private Customer customer;
	
	public GUIPayOffLoan() {
		customer = null;
		frame = new JFrame();	
		
		JLabel transferAmount = new JLabel("Payoff Amount");
		transferAmount.setBounds(250,200,160,25);
		frame.add(transferAmount);
		
		transferAmountText = new JTextField();
		transferAmountText.setBounds(400,200,165,25);
		frame.add(transferAmountText);
		
		success  = new JLabel("");
		success.setBounds(250,375,300,25);
		frame.add(success);
		
		success2  = new JLabel("");
		success2.setBounds(250,395,300,25);
		frame.add(success2);
		
		JLabel withdrawalFrom = new JLabel("Pay From");
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
		
		JLabel transferTo = new JLabel("Pay To");
		transferTo.setBounds(250,300,160,25);
		frame.add(transferTo);
		
		DefaultComboBoxModel loans = new DefaultComboBoxModel();
		
		if(Bank.getInstance().getCurrentUser() instanceof Customer) {
			customer = (Customer) Bank.getInstance().getCurrentUser();
			for(Loan loan: customer.getLoans()) {
				loans.addElement(loan.toString());
			}
		}
		
		combo2 = new JComboBox(loans);
		
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
					Loan loan = null;
					if ((combo.getSelectedIndex() != -1)) {//choose sender	
						sender = Bank.getInstance().getCurrentUser().getAccounts().get(combo.getSelectedIndex());			
					} else {
						success.setText("Choose an account");
					}
					
					if ((combo2.getSelectedIndex() != -1)) {//choose receiver
						loan = customer.getLoans().get(combo2.getSelectedIndex());
						//loan.payOffLoanAmount(amount);
					} else {
						success.setText("Choose a loan");
					}
					
					if(sender != null && loan != null) {
						if(amount>0 && amount<sender.getBalance()) {
							Bank.getInstance().transferMoney(sender, loan, amount);//TODO: payoffLoan.
							success.setText("Success! Paid "+sender.getCurrencyType().getSymbol()+ amount+ " to "+ loan.toString());
							success2.setText(loan.toString()+" current balance:  "+loan.getCurrencyType().getSymbol()+loan.getBalance());
						}else {
							success.setText("please provide valid amount.");
						}
					}
				} catch(Exception exception) {
					success.setText("provide valid info please");
					exception.printStackTrace();
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
