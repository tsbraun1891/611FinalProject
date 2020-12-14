import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GUIViewTransactionHistory {
	private DefaultTableModel tableModel;
	private JTable table;
	private JFrame frame;
	
	public GUIViewTransactionHistory(BalanceHandler bh) {
		prepareUI();
		ArrayList<Transaction> original = new ArrayList<Transaction>();
		if(bh instanceof Account) {
			Account account = (Account)bh;
			original = Bank.getInstance().getTransactionsForAccount(account);
		} else if(bh instanceof User) {
			User user = (User) bh;
			original = Bank.getInstance().getTransactionsForUser(user);
		}
        
        for(int i = 0; i<original.size();i++) {
        	int id = original.get(i).getID();
        	int senderId = original.get(i).getSenderID();
        	int receiverId = original.get(i).getReceiverID();
        	double amount = original.get(i).getTransactionAmount();
        	String currencyDesc = original.get(i).getCurrencyType().getDesc();
        	boolean senderUser = original.get(i).isSenderUser();
        	boolean receiverUser = original.get(i).isReceiverUser();
        	String date = original.get(i).getDate();
        	Object[] rowData = {id, senderId, receiverId, amount, currencyDesc, senderUser, receiverUser, date};
        	tableModel.addRow(rowData);
        }
	}
	
	public GUIViewTransactionHistory(User user, boolean ViewAccounts) {
		prepareUI();
		ArrayList<Transaction> userHistory = new ArrayList<Transaction>();
		userHistory = Bank.getInstance().getTransactionsForUser(user);
		for(int i = 0; i<userHistory.size();i++) {
        	int id = userHistory.get(i).getID();
        	int senderId = userHistory.get(i).getSenderID();
        	int receiverId = userHistory.get(i).getReceiverID();
        	double amount = userHistory.get(i).getTransactionAmount();
        	String currencyDesc = userHistory.get(i).getCurrencyType().getDesc();
        	boolean senderUser = userHistory.get(i).isSenderUser();
        	boolean receiverUser = userHistory.get(i).isReceiverUser();
        	String date = userHistory.get(i).getDate();
        	Object[] rowData = {id, senderId, receiverId, amount, currencyDesc, senderUser, receiverUser, date};
        	tableModel.addRow(rowData);
        }
		//iterate through all accounts of user
		for(Account account: user.getAccounts()) {
			ArrayList<Transaction> accountHistory = new ArrayList<Transaction>();
			accountHistory = Bank.getInstance().getTransactionsForAccount(account);
			for(int i = 0; i<accountHistory.size();i++) {
	        	int id = accountHistory.get(i).getID();
	        	int senderId = accountHistory.get(i).getSenderID();
	        	int receiverId = accountHistory.get(i).getReceiverID();
	        	double amount = accountHistory.get(i).getTransactionAmount();
	        	String currencyDesc = accountHistory.get(i).getCurrencyType().getDesc();
	        	boolean senderUser = accountHistory.get(i).isSenderUser();
	        	boolean receiverUser = accountHistory.get(i).isReceiverUser();
	        	String date = accountHistory.get(i).getDate();
	        	Object[] rowData = {id, senderId, receiverId, amount, currencyDesc, senderUser, receiverUser, date};
	        	tableModel.addRow(rowData);
	        }
		}
	}
	
	
	public void prepareUI() {
		JPanel panel = new JPanel(null);
		panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 200));
		tableModel = new DefaultTableModel();
		frame = new JFrame();
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(panel);
        frame.pack();
		frame.setVisible(true);
        
        String[] headerDesc = {"ID", "SenderID", "ReceiverId", "TransactionAmount", "CurrencyDesc", "SenderUser","ReceiverUser","Date"};
        tableModel.setColumnIdentifiers(headerDesc);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane transactionScroll = new JScrollPane(table);
        panel.add(transactionScroll, BorderLayout.CENTER);
	}
	
}
