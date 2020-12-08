import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIViewAcct {
	private JFrame frame;
	private JButton viewSavingTxnButton;
	private JButton viewCheckingTxnButton;
	private JButton convertCheckingCurrencyButton;
	private JButton convertSavingCurrencyButton;
	private JButton quitButton;
	private JButton backButton;
	
	public GUIViewAcct() {
		frame = new JFrame();
		
		//if customer.getCheckingAccount != null {
		JLabel checking = new JLabel("Checking Acct:");//+ account.getBalance()
		checking.setBounds(100, 150, 160, 25);

		frame.add(checking);
	
		viewCheckingTxnButton = new JButton("View Transaction");
		viewCheckingTxnButton.setBounds(350, 150, 160, 25);		
		addViewCheckingTxnButtonFunction();
		frame.add(viewCheckingTxnButton);
		
		convertCheckingCurrencyButton = new JButton("Convert Currency");
		convertCheckingCurrencyButton.setBounds(500, 150, 160, 25);		
		addConvertCheckingCurrencyButtonFunction();
		frame.add(convertCheckingCurrencyButton);
		
		//}
		
		//if customer.getSavingAccount != null
		JLabel saving = new JLabel("Saving Acct:");//+ account.getBalance()
		saving.setBounds(100, 200, 160, 25);
		frame.add(saving);
		
		viewSavingTxnButton = new JButton("View Transaction");
		viewSavingTxnButton.setBounds(350, 200, 160, 25);		
		addViewSavingTxnButtonFunction();
		frame.add(viewSavingTxnButton);
		
		convertSavingCurrencyButton = new JButton("Convert Currency");
		convertSavingCurrencyButton.setBounds(500, 200, 160, 25);		
		addConvertSavingCurrencyButtonFunction();
		frame.add(convertSavingCurrencyButton);
		
		//}
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
		frame.setVisible(true);
	}

	private void addViewCheckingTxnButtonFunction() {
		// TODO Auto-generated method stub
		viewCheckingTxnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIViewTransactionHistory history = new GUIViewTransactionHistory(AccountType.CHECKING);
				closeFrame();
			}		
		});
	}

	private void addConvertCheckingCurrencyButtonFunction() {
		// TODO Auto-generated method stub
		convertCheckingCurrencyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIConvertCurrency convert = new GUIConvertCurrency(AccountType.CHECKING);
				closeFrame();
			}		
		});
	}

	private void addViewSavingTxnButtonFunction() {
		// TODO Auto-generated method stub
		viewSavingTxnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIViewTransactionHistory history = new GUIViewTransactionHistory(AccountType.SAVING);
				closeFrame();
			}		
		});
	}

	private void addConvertSavingCurrencyButtonFunction() {
		// TODO Auto-generated method stub
		convertSavingCurrencyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIConvertCurrency convert = new GUIConvertCurrency(AccountType.SAVING);
				closeFrame();
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
