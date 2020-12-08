import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIConvertCurrency {
	private AccountType acctType;
	private JFrame frame;
	private JLabel success;
	private JLabel newBalance;
	private JButton convertToUSDButton;
	private JButton convertToEUROButton;
	private JButton convertToWHATButton;
	private JButton quitButton;
	private JButton backButton;
	
	public GUIConvertCurrency(AccountType acctType) {
		frame = new JFrame();
		this.acctType = acctType;
		
		if(acctType.equals(AccountType.CHECKING)) {
			JLabel checking = new JLabel("Checking Acct:");//+ account.getBalance()
			checking.setBounds(200, 150, 160, 25);
			frame.add(checking);
		} else if(acctType.equals(AccountType.SAVING)) {
			JLabel checking = new JLabel("Saving Acct:");//+ account.getBalance()
			checking.setBounds(200, 150, 160, 25);
			frame.add(checking);
		}
		
		convertToUSDButton = new JButton("Convert to USD");
		convertToUSDButton.setBounds(150, 200, 160, 25);		
		addconvertToUSDButtonFunction();
		frame.add(convertToUSDButton);
		
		convertToEUROButton = new JButton("Convert to EURO");
		convertToEUROButton.setBounds(300, 200, 160, 25);		
		addconvertToEUROButtonFunction();
		frame.add(convertToEUROButton);
		
		convertToWHATButton = new JButton("Convert to WHAT");
		convertToWHATButton.setBounds(450, 200, 160, 25);		
		addconvertToWHATButtonFunction();
		frame.add(convertToWHATButton);
		
		success  = new JLabel("");
		success.setBounds(200, 250, 160, 25);
		frame.add(success);
		
		newBalance  = new JLabel("");
		newBalance.setBounds(200, 300, 160, 25);
		frame.add(newBalance);
		
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
	
	private void addconvertToWHATButtonFunction() {
		// TODO Auto-generated method stub
		convertToWHATButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ConvertToWhat()
				printBalanceInNewCurrency();
			}		
		});
	}

	private void addconvertToUSDButtonFunction() {
		// TODO Auto-generated method stub
		convertToUSDButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ConvertToUSD()
				printBalanceInNewCurrency();
			}		
		});
		
	}

	private void addconvertToEUROButtonFunction() {
		// TODO Auto-generated method stub
		convertToEUROButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ConvertToEURO()
				printBalanceInNewCurrency();
			}		
		});
		
	}
	
	private void printBalanceInNewCurrency() {
		// TODO Auto-generated method stub
		success.setText("Success! Now you have ...");
		newBalance.setText("Account.getBalance()");	//////////getBalance
	}

	private void addBackButtonFunction() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIViewAcct view = new GUIViewAcct();
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
