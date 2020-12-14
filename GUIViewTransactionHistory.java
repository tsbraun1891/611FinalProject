import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GUIViewTransactionHistory {
	private DefaultTableModel tableModel;
	private JTable table;
	private JFrame frame;
	public GUIViewTransactionHistory(Account account) {
		tableModel = new DefaultTableModel();
		frame = new JFrame();
		
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        
        String[] headerDesc = {"ID", "SenderID", "ReceiverId", "TransactionAmount", "CurrencyDesc", "SenderUser","ReceiverUser","Date"};
        tableModel.setColumnIdentifiers(headerDesc);
        table = new JTable(tableModel);
        
        ArrayList<Transaction> original = Bank.getInstance().getTransactionsForAccount(account);
        for(int i = 0; i<original.size();i++) {
        	int id = original.get(i).getID();
        	int senderID = original.get(i).getReceiver()
        	int receiverId = original.get(i).getSender()
        	double amount = original.get(i).getTransactionAmount();
        	String currencyDes = original.get(i).getCurrencyType().getDesc();
        	boolean senderUser = original.get(i).isSenderUser();
        	boolean receiverUser = original.get(i).isReceiverUser();
        	String date = original.get(i).getDate();
        }
	}
	
}
