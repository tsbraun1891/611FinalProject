import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class represents the welcome page window.
 * @author ling
 *
 */

public class GUIHome {
	private JFrame frame;
	private JButton button;

	public GUIHome() {
		// TODO Auto-generated constructor stub
		frame = new JFrame();
		button = new JButton("Start");
		button.setBounds(365, 450, 80, 25);
		addStartButtonFunction();
		frame.add(button);
		addBackgroundPic();
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addStartButtonFunction() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIChooseUserType choose = new GUIChooseUserType();
				closeFrame();
			}
		});
	}
	
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("image/Welcome.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
	}
	
	public void closeFrame() {
		frame.dispose();
	}
}
