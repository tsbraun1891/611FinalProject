
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * This class represents the two login options: CustomerLogin, AdminLogin.
 * @author ling
 *
 */


public class GUIChooseUserType {
	private JFrame frame;
	private JButton userButton;
	private JButton managerButton;
	private JButton quitButton;

	public GUIChooseUserType() {
		frame = new JFrame();	
		userButton = new JButton("I am Customer");
		userButton.setBounds(200, 270, 160, 25);		
		addUserButtonFunction();
		frame.add(userButton);
		
		
		managerButton = new JButton("I am Manager");
		managerButton.setBounds(450, 270, 160, 25);
		addManagerButtonFunction();
		frame.add(managerButton);
		
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
		ImageIcon icon = new ImageIcon("image/bg.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
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
