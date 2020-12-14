import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
/**
 * This class represents the window that allows customer to request loan.
 * @author ling
 *
 */
public class GUILoanRequest {
	private JFrame frame;
	private JButton approveButton;
	private JButton denyButton;
	private JButton quitButton;
	private JButton backButton;
	private JComboBox combo;
	private JLabel success;
	public GUILoanRequest() {
		frame = new JFrame();
		
		JLabel chooseLoan = new JLabel("Pick a loan request");
		chooseLoan.setBounds(250,200,160,25);
		frame.add(chooseLoan);
		
		DefaultComboBoxModel loans = new DefaultComboBoxModel();
		
		for(Loan loan: Bank.getInstance().getAdmin().getRequestedLoans()) {
			loans.addElement(loan.toString());
		}
		combo = new JComboBox(loans);
		JScrollPane loanPane= new JScrollPane(combo);
		loanPane.setBounds(400,190,170, 45);
		frame.add(loanPane);
		
		success  = new JLabel("");
		success.setBounds(230,330,300,25);
		frame.add(success);
	
		approveButton = new JButton("Approve");
		approveButton.setBounds(230, 300, 160, 25);		
		addApproveButtonFunction();
		frame.add(approveButton);
	
		denyButton = new JButton("Deny");
		denyButton.setBounds(430, 300, 160, 25);		
		addDenyButtonFunction();
		frame.add(denyButton);
		
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
	
	private void addDenyButtonFunction() {
		// TODO Auto-generated method stub
		denyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (combo.getSelectedIndex() != -1) {
					Loan chosen = Bank.getInstance().getAdmin().getRequestedLoans().get(combo.getSelectedIndex());
					Bank.getInstance().getAdmin().denyLoan(chosen);
					success.setText("Deny Loan Success!");
				} else {
					success.setText("Choose an loan");
				}
			}		
		});
	}

	private void addApproveButtonFunction() {
		// TODO Auto-generated method stub
		approveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (combo.getSelectedIndex() != -1) {
					Loan chosen = Bank.getInstance().getAdmin().getRequestedLoans().get(combo.getSelectedIndex());
					Bank.getInstance().getAdmin().approveLoan(chosen);
					success.setText("Approve Loan Success!");
				} else {
					success.setText("Choose an loan");
				}
			}		
		});
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
