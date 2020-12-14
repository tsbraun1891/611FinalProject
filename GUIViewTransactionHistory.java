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
/**
 * This class represents the window that prompts a transaction history record given different input.
 * @author ling
 *
 */

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
        setColWidth(table.getColumnModel());
	}
	/**
	 * Admin checks up on specific customer given username. 
	 * @param user
	 * @param ViewAccounts
	 */
	public GUIViewTransactionHistory(User user, boolean ViewBalanceHandler) {		
		getAccountTable(user);
		getLoanTable(user);
	}
	
	/**
	 * This builds a daily report.
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
        setColWidth(table.getColumnModel()); 
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
	
	public void setColWidth(TableColumnModel model) {
		for(int i = 0; i<model.getColumnCount();i++) {
			model.getColumn(i).setPreferredWidth(100);
		}
	}
	
	public void getAccountTable(User user) {
		JFrame frame1 = new JFrame();
		JPanel panel = new JPanel(null);
		panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 200));
		DefaultTableModel tableModel1 = new DefaultTableModel();
		frame1 = new JFrame();
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setLocationRelativeTo(null);
		frame1.setContentPane(panel);
        frame1.pack();
		frame1.setVisible(true);
        
        String[] headerDesc = {"Type", "Owner", "CurrencyDesc", "Balance", "FeeRate", "IntThreshold","IntRate","ID"};
        tableModel1.setColumnIdentifiers(headerDesc);
        JTable table1 = new JTable(tableModel1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane transactionScroll = new JScrollPane(table1);
        panel.add(transactionScroll, BorderLayout.CENTER);
        
        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts = user.getAccounts();
        
        for(int i = 0; i<accounts.size();i++) {
        	String type = null;
        	if(accounts.get(i) instanceof Savings) {
        		type = "Savings";
        	} else {
        		type = "Checking";
        	}
        	int owner = accounts.get(i).getOwner().getUserId();
        	String currencyDesc = accounts.get(i).getCurrencyType().getDesc();
        	double balance = accounts.get(i).getBalance();
        	double feeRate = accounts.get(i).getFeeRate();
        	double intThreshold = 0;
        	double intRate = 0;
        	if(accounts.get(i) instanceof Savings) {
        		Savings saving = (Savings)accounts.get(i);
        		intThreshold = saving.getThreshold();
        		intRate = saving.getInterestRate();
        	} 
        	int Id = accounts.get(i).getID();
        	Object[] rowData = {type, owner, currencyDesc, balance, feeRate, intThreshold, intRate, Id};
        	tableModel1.addRow(rowData);
        }
        setColWidth(table1.getColumnModel()); 
	}
	
	public void getLoanTable(User user) {
		JFrame frame1 = new JFrame();
		JPanel panel = new JPanel(null);
		panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 200));
		DefaultTableModel tableModel1 = new DefaultTableModel();
		frame1 = new JFrame();
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setLocation(200,300);
		frame1.setContentPane(panel);
        frame1.pack();
		frame1.setVisible(true);
        
        String[] headerDesc = {"Owner", "Loaner", "CurrencyDesc", "Balance", "IntRate","Approved","ID"};
        tableModel1.setColumnIdentifiers(headerDesc);
        JTable table1 = new JTable(tableModel1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane transactionScroll = new JScrollPane(table1);
        panel.add(transactionScroll, BorderLayout.CENTER);
        
        ArrayList<Loan> loans = new ArrayList<Loan>();
        Customer customer = null;
        if(user instanceof Customer) {
        	customer = (Customer) user;
        }
        loans = customer.getLoans();
        
        for(int i = 0; i<loans.size();i++) {
        	int owner = loans.get(i).getOwner().getUserId();
        	int loaner = loans.get(i).getLoaner().getUserId();
        	String currencyDesc = loans.get(i).getCurrencyType().getDesc();
        	double balance = loans.get(i).getBalance();
        	double feeRate = loans.get(i).getInterestRate();
        	int approved = 0;
        	if(loans.get(i).isApproved()) {
        		approved = 1;
        	}
        	int Id = loans.get(i).getID();
        	Object[] rowData = {owner, loaner, currencyDesc, balance, feeRate, approved,Id};
        	tableModel1.addRow(rowData);
        }
        setColWidth(table1.getColumnModel()); 
	}
}
