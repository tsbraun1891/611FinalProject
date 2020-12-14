import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUICustomerHome {
	private JFrame frame;
	private JButton depositButton;
	private JButton withdrawalButton;
	private JButton openAcctButton;
	private JButton transferButton;
	private JButton requestLoanButton;
	private JButton viewAcctButton;//Txn - shortform for transaction
	private JButton cashInButton;
	private JButton cashOutButton;
	private JButton quitButton;
	private JButton backButton;
	
	public GUICustomerHome(){
		frame = new JFrame();
		
		viewAcctButton = new JButton("View Account");
		viewAcctButton.setBounds(300, 150, 200, 25);		
		addViewAcctButtonFunction();
		frame.add(viewAcctButton);
		
		depositButton = new JButton("Deposit");
		depositButton.setBounds(300, 180, 200, 25);
		addDepositButtonFunction();
		frame.add(depositButton);
		
		withdrawalButton = new JButton("Withdrawal");
		withdrawalButton.setBounds(300, 210, 200, 25);
		addWithdrawalButtonFunction();
		frame.add(withdrawalButton);
		
		transferButton = new JButton("Transfer");
		transferButton.setBounds(300, 240, 200, 25);
		addTransferButtonFunction();
		frame.add(transferButton);
		
		requestLoanButton = new JButton("Request Loan");
		requestLoanButton.setBounds(300, 270, 200, 25);
		addRequestLoanButtonFunction();
		frame.add(requestLoanButton);
		
		openAcctButton = new JButton("Open New Account");
		openAcctButton.setBounds(300, 300, 200, 25);
		addOpenAcctButtonFunction();
		frame.add(openAcctButton);
		
		cashInButton = new JButton("Cash In");
		cashInButton.setBounds(300, 330, 200, 25);
		addCashInButtonFunction();
		frame.add(cashInButton);
		
		cashOutButton = new JButton("Cash Out");
		cashOutButton.setBounds(300, 360, 200, 25);
		addCashOutButtonFunction();
		frame.add(cashOutButton);
		
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
	
	private void addCashOutButtonFunction() {
		// TODO Auto-generated method stub
		cashOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUICashOut out = new GUICashOut();
				closeFrame();
			}		
		});
	}

	private void addCashInButtonFunction() {
		// TODO Auto-generated method stub
		cashInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUICashIn in = new GUICashIn();
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
	
	private void addBackButtonFunction() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUILogin login = new GUILogin(UserType.CUSTOMER);
				closeFrame();
			}		
		});
	}

	private void addOpenAcctButtonFunction() {
		// TODO Auto-generated method stub
		openAcctButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIOpenNewAccount acct = new GUIOpenNewAccount();
				closeFrame();
			}		
		});
	}

	private void addRequestLoanButtonFunction() {
		// TODO Auto-generated method stub
		requestLoanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIRequestLoan loan = new GUIRequestLoan();
				closeFrame();
			}		
		});
	}

	private void addTransferButtonFunction() {
		// TODO Auto-generated method stub
		transferButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUITransfer transfer = new GUITransfer();
				closeFrame();
			}		
		});
		
	}

	private void addWithdrawalButtonFunction() {
		// TODO Auto-generated method stub
		withdrawalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIWithdrawal withdrawal = new GUIWithdrawal();
				closeFrame();
			}		
		});
	}

	private void addDepositButtonFunction() {
		// TODO Auto-generated method stub
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIDeposit deposit = new GUIDeposit();
				closeFrame();
			}		
		});
	}

	private void addViewAcctButtonFunction() {
		// TODO Auto-generated method stub
		viewAcctButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIViewAcct viewAcct = new GUIViewAcct();
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
