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
	
	public GUIViewAcct() {
		frame = new JFrame();
		
		
		JLabel chooseAccount = new JLabel("Choose Account");
		chooseAccount.setBounds(250,110,160,25);
		frame.add(chooseAccount);
		
		DefaultComboBoxModel accounts = new DefaultComboBoxModel();
		accounts.addElement("Your wallet");
		for(Account account: Bank.getInstance().getCurrentUser().getAccounts()) {
			accounts.addElement(account.toString());
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
					if(combo.getSelectedIndex() != 0) {
						Account chosen = Bank.getInstance().getCurrentUser().getAccounts().get(combo.getSelectedIndex()-1);
						String s1 = chosen.getCurrencyType().getSymbol();
						String s = String.valueOf(chosen.getBalance());
						balance.setText(s1+s);
					} else {//show wallet's balance
						User user = Bank.getInstance().getCurrentUser();
						String s1 = user.getCurrencyType().getSymbol();
						String s = String.valueOf(user.getBalance());
						balance.setText(s1+s);
					}
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
				GUIViewTransactionHistory history = new GUIViewTransactionHistory(AccountType.CHECKING);
				closeFrame();
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
					if(combo.getSelectedIndex() != 0) {
						Account chosen = Bank.getInstance().getCurrentUser().getAccounts().get(combo.getSelectedIndex()-1);
						GUIConvertCurrency convert = new GUIConvertCurrency(chosen);
					} else {//show wallet's balance
						GUIConvertCurrency convert = new GUIConvertCurrency(Bank.getInstance().getCurrentUser());
					}
				} else {
					balance.setText("Choose an account/wallet");
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
