import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIAdminHome {
	private JFrame frame;
	private JButton dailyReportButton;
	private JButton checkCustomerButton;
	private JButton quitButton;
	
	public GUIAdminHome() {
		frame = new JFrame();
		dailyReportButton = new JButton("Get Daily Report");
		dailyReportButton.setBounds(300, 210, 200, 25);
		addDailyReportButtonFunction();
		frame.add(dailyReportButton);
		
		checkCustomerButton = new JButton("Check Customer");//????????????????????
		checkCustomerButton.setBounds(300, 240, 200, 25);
		addCheckCustomerButtonFunction();
		frame.add(checkCustomerButton);
		
		quitButton = new JButton("Quit");
		quitButton.setBounds(710, 500, 80, 25);
		addQuitButtonFunction();
		frame.add(quitButton);
		
		addBackgroundPic();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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

	private void addCheckCustomerButtonFunction() {
		// TODO Auto-generated method stub
		checkCustomerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUICheckCustomer check = new GUICheckCustomer();
				closeFrame();
			}		
		});
	}

	private void addDailyReportButtonFunction() {
		// TODO Auto-generated method stub
		dailyReportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUIDailyReport report = new GUIDailyReport();
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
