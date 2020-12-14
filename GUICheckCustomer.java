import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUICheckCustomer {
	private JFrame frame;
	private JButton searchButton;
	private JButton getAllCustomerButton;
	private JButton quitButton;
	private JButton backButton;
	private JTextField usernameText;
	private JLabel success;
	private JLabel success2;
	
	public GUICheckCustomer() {
		frame = new JFrame();
		
		JLabel title1 = new JLabel("Search for specific client: ");
		title1.setBounds(190,170,200,25);
		frame.add(title1);
		
		JLabel usernameTitle = new JLabel("Client Username");
		usernameTitle.setBounds(190,200,160,25);
		frame.add(usernameTitle);
		
		usernameText = new JTextField();
		usernameText.setBounds(310,200,165,25);
		frame.add(usernameText);
		
		searchButton = new JButton("Search");
		searchButton.setBounds(490, 200, 100, 25);		
		addSearchButtonFunction();
		frame.add(searchButton);
		
		success  = new JLabel("");
		success.setBounds(190,230,300,25);
		frame.add(success);
		
		success2  = new JLabel("");
		success2.setBounds(190,330,300,25);
		frame.add(success2);
		
		JLabel title2 = new JLabel("Check all clients: ");
		title2.setBounds(190,300,200,25);
		//frame.add(title2);
		
		getAllCustomerButton = new JButton("Check");
		getAllCustomerButton.setBounds(490, 300, 100, 25);
		addGetAllCustomerButtonFunction();
		//frame.add(getAllCustomerButton);
		
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
	
	private void addGetAllCustomerButtonFunction() {
		// TODO Auto-generated method stub
		getAllCustomerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				
			}
		});
	}

	private void addSearchButtonFunction() {
		// TODO Auto-generated method stub	
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User user = Bank.getInstance().getUserByUsername(usernameText.getText());
				if(user!=null) {
					Bank.getInstance().getTransactionsForUser(user);
					
				
				} else {
					success.setText("Username not found.");
				}
				
			}
		});
	}
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("image/bg.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
	}
	
	private void addBackButtonFunction() {
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIAdminHome h = new GUIAdminHome();
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
	
	public void closeFrame() {
		frame.dispose();
	}
}
