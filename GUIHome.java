import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class GUIHome {

	public GUIHome() {
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame();
		JButton button = new JButton("Start");
		button.setBounds(350, 450, 80, 25);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIChooseUserType choose = new GUIChooseUserType();
				frame.dispose();
			}
			
		});
		frame.add(button);
		
		ImageIcon icon = new ImageIcon("Welcome.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
		
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
