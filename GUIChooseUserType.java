
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class GUIChooseUserType {
	private JFrame frame;
	private JButton userButton;
	private JButton managerButton;

	public GUIChooseUserType() {
		frame = new JFrame();	
		userButton = new JButton("I am User");
		userButton.setBounds(200, 270, 160, 25);		
		addUserButtonFunction();
		frame.add(userButton);
		
		
		managerButton = new JButton("I am Manager");
		managerButton.setBounds(450, 270, 160, 25);
		addManagerButtonFunction();
		frame.add(managerButton);
		
		addBackgroundPic();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
	}
	
	public void addUserButtonFunction() {
		userButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUILogin login = new GUILogin(UserType.CUSTOMER);
				closeFrame();
			}	
		});
	}
	
	public void addManagerButtonFunction() {
		managerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUILogin login = new GUILogin(UserType.ADMIN);
				closeFrame();
			}		
		});
	}
	
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("Welcome.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
	}
	
	public void closeFrame() {
		frame.dispose();
	}
}
