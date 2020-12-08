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

public class GUITransferTo {
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JLabel success;
	private JComboBox combo;
	private JTextField transferAmountText;
	private JTextField transferToText;
	
	public GUITransferTo() {
		frame  = new JFrame();
		
		JLabel transferAmount = new JLabel("Transfer Amount");
		transferAmount.setBounds(250,200,160,25);
		frame.add(transferAmount);
		
		transferAmountText = new JTextField();
		transferAmountText.setBounds(400,200,165,25);
		frame.add(transferAmountText);
		
		success  = new JLabel("");
		success.setBounds(250,350,300,25);
		frame.add(success);
		
		JLabel withdrawalFrom = new JLabel("Withdrawal From");
		withdrawalFrom.setBounds(250,250,160,25);
		frame.add(withdrawalFrom);
		
		DefaultComboBoxModel accounts = new DefaultComboBoxModel();
		accounts.addElement("account1");
		accounts.addElement("account2");
		accounts.addElement("account3");
		
		combo = new JComboBox(accounts);
		combo.setSelectedIndex(0);
		
		JScrollPane accountPane= new JScrollPane(combo);
		accountPane.setBounds(400, 250,150, 30);
		frame.add(accountPane);
		
		JLabel transferTo = new JLabel("Send Money To");
		transferTo.setBounds(250,300,160,25);
		frame.add(transferTo);
		
		transferToText = new JTextField();
		transferToText.setBounds(400,300,165,25);
		frame.add(transferToText);
		
		JLabel hint = new JLabel("Please provide username of the recipient");
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
					String data = "";//TODO: if transferToText is found
		            if (combo.getSelectedIndex() != -1) {                     
		               data = "Accounts Selected: " 
		                  + combo.getItemAt
		                  (combo.getSelectedIndex());             
		            }              
		            success.setText(data);
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
