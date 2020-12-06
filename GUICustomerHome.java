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
	private JButton viewTxnButton;//Txn - shortform for transaction
	private JButton quitButton;
	
	public GUICustomerHome() {
		frame = new JFrame();
		
		viewTxnButton = new JButton("View Balance/Transaction");
		viewTxnButton.setBounds(300, 150, 200, 25);		
		addViewTxnButtonFunction();
		frame.add(viewTxnButton);
		
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
		
		quitButton = new JButton("Quit");
		quitButton.setBounds(710, 500, 80, 25);
		addQuitButtonFunction();
		frame.add(quitButton);
		
		addBackgroundPic();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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
		withdrawalButton.addActionListener(new ActionListener() {
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

	private void addViewTxnButtonFunction() {
		// TODO Auto-generated method stub
		viewTxnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIViewTransaction viewTxn = new GUIViewTransaction();
				closeFrame();
			}		
		});
	}
	
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("bg.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
	}
	
	public void closeFrame() {
		frame.dispose();
	}
}
