import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUIChooseUserType {

	public GUIChooseUserType() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		
		panel.setLayout(null);
		
		JButton button = new JButton("I am User");
		button.setBounds(100, 80, 160, 25);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUILogin login = new GUILogin(UserType.CUSTOMER);
				frame.dispose();
			}	
		});
		panel.add(button);	
		
		JButton button2 = new JButton("I am Manager");
		button2.setBounds(300, 80, 160, 25);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUILogin login = new GUILogin(UserType.ADMIN);
				frame.dispose();
			}		
		});
		panel.add(button2);
		
		
		
		frame.setVisible(true);	
	}
}
