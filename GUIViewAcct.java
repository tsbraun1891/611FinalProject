import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class GUIViewAcct {
	private JFrame frame;
	private JButton viewTransactionButton;
	private JButton convertCurrencyButton;
	private JButton viewBalanceButton;
	private JButton quitButton;
	private JButton backButton;
	private JComboBox combo;
	private JLabel balance;
	private Customer customer;
	
	public GUIViewAcct() {
		frame = new JFrame();
		
		customer = null;
		User user = Bank.getInstance().getCurrentUser();
		if(user instanceof Customer) {
			customer = (Customer) user;
		}
		
		JLabel chooseAccount = new JLabel("Choose Account");
		chooseAccount.setBounds(250,110,160,25);
		frame.add(chooseAccount);
		
		DefaultComboBoxModel accounts = new DefaultComboBoxModel();
		accounts.addElement("Your wallet");
		for(Account account: Bank.getInstance().getCurrentUser().getAccounts()) {
			accounts.addElement(account);
		}
		for(Loan loan: customer.getLoans()) {
			accounts.addElement(loan);
		}
		
		combo = new JComboBox(accounts);
		JScrollPane accountPane= new JScrollPane(combo);
		accountPane.setBounds(400, 100,170, 45);
		frame.add(accountPane);
	
		viewBalanceButton = new JButton("View Balance");
		viewBalanceButton.setBounds(150, 300, 160, 25);		
		addViewBalanceButtonFunction();
		frame.add(viewBalanceButton);
	
		viewTransactionButton = new JButton("View Transaction");
		viewTransactionButton.setBounds(350, 300, 160, 25);		
		addViewTransactionButtonFunction();
		frame.add(viewTransactionButton);
		
		convertCurrencyButton = new JButton("Convert Currency");
		convertCurrencyButton.setBounds(550, 300, 160, 25);		
		addConvertCurrencyButtonFunction();
		frame.add(convertCurrencyButton);
		
		JLabel title = new JLabel("balance:  ");
		title.setBounds(250,170,300,25);
		frame.add(title);
		
		balance  = new JLabel("");
		balance.setBounds(330,170,300,25);
		frame.add(balance);
		
		
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

	private void addViewBalanceButtonFunction() {
		// TODO Auto-generated method stub
		viewBalanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((combo.getSelectedIndex() != -1)) {		
					BalanceHandler bh = (BalanceHandler) combo.getSelectedItem();
					String s1 = bh.getCurrencyType().getSymbol();
					String s = String.valueOf(bh.getBalance());
					balance.setText(s1+s);	
				} else {
					balance.setText("Choose an account/wallet");
				}
			}		
		});
		
	}

	private void addViewTransactionButtonFunction() {
		// TODO Auto-generated method stub
		viewTransactionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if ((combo.getSelectedIndex() != -1)) {
					BalanceHandler bh = (BalanceHandler) combo.getSelectedItem();
					GUIViewTransactionHistory view = new GUIViewTransactionHistory(bh);
				} else {
					balance.setText("Choose an account/wallet/loan");
				}
			}		
		});
	}

	private void addConvertCurrencyButtonFunction() {
		// TODO Auto-generated method stub
		convertCurrencyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((combo.getSelectedIndex() != -1)) {
					BalanceHandler bh = (BalanceHandler) combo.getSelectedItem();
					GUIConvertCurrency convert = new GUIConvertCurrency(bh);
					closeFrame();
				} else {
					balance.setText("Choose an account/wallet/loan");
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
