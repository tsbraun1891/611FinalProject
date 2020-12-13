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
	private JButton convertToYenButton;
	private JButton quitButton;
	private JButton backButton;
	private BalanceHandler handler;
	
	public GUIConvertCurrency(BalanceHandler bh) {
		frame = new JFrame();
		
		handler = bh;
		
		JLabel title = new JLabel("balance:");
		title.setBounds(200, 150, 160, 25);
		frame.add(title);
		
		String s = String.valueOf(bh.getBalance());
		String s1 = bh.getCurrencyType().getSymbol();
		JLabel balance = new JLabel(s1+s);
		balance.setBounds(300, 150, 160, 25);
		frame.add(balance);
		
		
		convertToUSDButton = new JButton("Convert to USD");
		convertToUSDButton.setBounds(150, 200, 160, 25);		
		addconvertToUSDButtonFunction();
		frame.add(convertToUSDButton);
		
		convertToEUROButton = new JButton("Convert to EURO");
		convertToEUROButton.setBounds(300, 200, 160, 25);		
		addconvertToEUROButtonFunction();
		frame.add(convertToEUROButton);
		
		convertToYenButton = new JButton("Convert to YEN");
		convertToYenButton.setBounds(450, 200, 160, 25);		
		addconvertToYENButtonFunction();
		frame.add(convertToYenButton);
		
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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void addconvertToYENButtonFunction() {
		// TODO Auto-generated method stub
		convertToYenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Currency yen = Bank.getInstance().getCurrencyTypes().get(2);
				double newAmount = handler.setCurrency(yen);
				success.setText("Success! Now you have ...");
				String s = String.valueOf(newAmount);
				String s1 = handler.getCurrencyType().getSymbol();
				newBalance.setText(s1+s);
			}		
		});
	}

	private void addconvertToUSDButtonFunction() {
		// TODO Auto-generated method stub
		convertToUSDButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Currency usd = Bank.getInstance().getCurrencyTypes().get(0);
				double newAmount = handler.setCurrency(usd);
				success.setText("Success! Now you have ...");
				String s = String.valueOf(newAmount);
				String s1 = handler.getCurrencyType().getSymbol();
				newBalance.setText(s1+s);
			}		
		});
		
	}

	private void addconvertToEUROButtonFunction() {
		// TODO Auto-generated method stub
		convertToEUROButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Currency euro = Bank.getInstance().getCurrencyTypes().get(1);
				double newAmount = handler.setCurrency(euro);
				success.setText("Success! Now you have ...");
				String s = String.valueOf(newAmount);
				String s1 = handler.getCurrencyType().getSymbol();
				newBalance.setText(s1+s);
			}		
		});
		
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
