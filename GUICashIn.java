import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUICashIn {
	private JFrame frame;
	private JButton submitButton;
	private JButton quitButton;
	private JButton backButton;
	private JLabel success;
	private JTextField amountText;
	
	public GUICashIn() {
		frame = new JFrame();
		
		JLabel title0 = new JLabel("Cashing in...");
		title0.setBounds(250,150,160,25);
		frame.add(title0);
		
		JLabel title = new JLabel("Please Insert Cash");
		title.setBounds(250,200,160,25);
		frame.add(title);
		
		amountText = new JTextField();
		amountText.setBounds(400,200,165,25);
		frame.add(amountText);

		success  = new JLabel("");
		success.setBounds(250,350,300,25);
		frame.add(success);
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(250,300, 80, 25);
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
	
	public void addSubmitButtonFunction() {
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					double fundAmount = Double.parseDouble(amountText.getText());	
					if(fundAmount >= 0) {
						Bank.getInstance().addMoneyToWallet(Bank.getInstance().getCurrentUser(), fundAmount);
						success.setText("Success! Add fund "+fundAmount+" to wallet! ");
					} else {
						success.setText("please enter valid fund amount. can't be negative. ");
					}
				}catch(Exception ex) {
					success.setText("please enter valid fund amount. can only be consisted with number.");
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
