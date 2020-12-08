import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUITransfer {
	private JFrame frame;
	private JButton transferToButton;
	private JButton transferBetweenButton;
	private JButton quitButton;
	private JButton backButton;
	
	public GUITransfer() {
		frame = new JFrame();	
		transferToButton = new JButton("Send Money To");
		transferToButton.setBounds(100, 270, 250, 25);		
		addTransferToFunction();
		frame.add(transferToButton);
		
		
		transferBetweenButton = new JButton("Transfer Between My Accounts");
		transferBetweenButton.setBounds(450, 270, 250, 25);
		addTransferBetweenButtonFunction();
		frame.add(transferBetweenButton);
		
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
		frame.setVisible(true);
	}
	
	public void addTransferToFunction() {
		transferToButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUITransferTo to = new GUITransferTo();
				closeFrame();
			}	
		});
	}
	
	public void addTransferBetweenButtonFunction() {
		transferBetweenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUITransferBetween between = new GUITransferBetween();
				closeFrame();
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
