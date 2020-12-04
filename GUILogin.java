import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GUILogin {
	private Timer timer = null;

	public GUILogin(UserType userType) {
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10,20,80,25);
		panel.add(userLabel);
		
		JTextField userText = new JTextField();
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		
		
		JLabel pswLabel = new JLabel("Password");
		pswLabel.setBounds(10,50,80,25);
		panel.add(pswLabel);
		
		JTextField pswText = new JTextField();
		pswText.setBounds(100,50,165,25);
		panel.add(pswText);
		
		JLabel success  = new JLabel("");
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		JButton button = new JButton("Login");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = userText.getText();
				String password = pswText.getText();
				//TODO: search database to see if userName and password match any record.
				if(userType.equals(UserType.CUSTOMER)) {//search customer
					timer = new Timer(1000, new ActionListener() {//after 1 sec, go to Customer Menu
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							GUICustomerHome customerHome = new GUICustomerHome();
							timer.stop();
							frame.dispose();
						}
					});
					if(userName.equals("Ling") && password.equals("abc")) {
						success.setText("Customer Login Successful!");
						timer.start();
						
					} else {
						success.setText("Invalid username or password.");
					}
				} else if(userType.equals(UserType.ADMIN)) { //search manager
					timer = new Timer(1000, new ActionListener() {//after 1 sec, go to Customer Menu
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							GUIAdminHome adminHome = new GUIAdminHome();
							timer.stop();
							frame.dispose();
						}
					});
					if(userName.equals("Manager") && password.equals("abc")) {
						success.setText("Admin Login Successful!");
						timer.start();
					} else {
						success.setText("Invalid username or password.");
					}
				}
			}
			
		});
		panel.add(button);	
		frame.setVisible(true);		
	}
}
