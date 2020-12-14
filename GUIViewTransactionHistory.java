import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GUIViewTransactionHistory {
	private DefaultTableModel tableModel;
	private JTable table;
	private JFrame frame;
	public GUIViewTransactionHistory(Account account) {
		tableModel = new DefaultTableModel();
		frame = new JFrame();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        
        String[] headerDesc = {"ID", "SenderID", "ReceiverId", "TransactionAmount", "CurrencyDesc", "SenderUser","ReceiverUser","Date"};
        tableModel.setColumnIdentifiers(headerDesc);
        table = new JTable(tableModel);
        JScrollPane transactionScroll = new JScrollPane(table);
        
        frame.add(table);
        
        ArrayList<Transaction> original = Bank.getInstance().getTransactionsForAccount(account);
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
	
}
