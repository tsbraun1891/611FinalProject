import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


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
		} if(bh instanceof User) {
			User user = (User) bh;
			original = Bank.getInstance().getTransactionsForWallet(user);
		} else if(bh instanceof Loan) {
			Loan loan = (Loan)bh;
			original = Bank.getInstance().getTransactionsForLoan(loan);
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
        setColWidth();
	}
	/**
	 * Admin checks up specific customer
	 * @param user
	 * @param ViewAccounts
	 */
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
		setColWidth();
	}
	
	/**
	 * 
	 */
	
	public GUIViewTransactionHistory(boolean dailyReport) {
		prepareUI();
		ArrayList<Transaction> original = new ArrayList<Transaction>();
		original = Bank.getInstance().getDailyReport();
        
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
        setColWidth(); 
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
        
        String[] headerDesc = {"ID", "SenderID", "ReceiverId", "Amount", "CurrencyDesc", "SenderUser","ReceiverUser","Date"};
        tableModel.setColumnIdentifiers(headerDesc);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane transactionScroll = new JScrollPane(table);
        panel.add(transactionScroll, BorderLayout.CENTER);
	}
	
	public void setColWidth() {
		TableColumnModel model = table.getColumnModel();
		model.getColumn(0).setPreferredWidth(100);
		model.getColumn(1).setPreferredWidth(100);
		model.getColumn(2).setPreferredWidth(100);
		model.getColumn(3).setPreferredWidth(100);
		model.getColumn(4).setPreferredWidth(100);
		model.getColumn(5).setPreferredWidth(100);
		model.getColumn(6).setPreferredWidth(100);
		model.getColumn(7).setPreferredWidth(100);
		
	}
}
