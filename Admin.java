/**
 * Admin is an extension of the User class. The admin is an
 * elavated user that has the ability to see other users' 
 * information as well as approving or denying loans.
 */

import java.util.ArrayList;

public class Admin extends User {
	private ArrayList<Loan> requestedLoans;
	private Bank bank;
	
	public Admin(int userId, String fistName, String lastName, String username, String password, double balance, Currency currency, Bank bank) {
		super(userId, fistName, lastName, username, password, balance, currency);

		requestedLoans = new ArrayList<>();
		this.bank = bank;
	}

	public boolean approveLoan(Loan loan) {
		if(this.getBalance() >= loan.getBalance()) {
			loan.approveLoan();

			requestedLoans.remove(loan);

			bank.saveData();

			return true;
		}

		return false;		
	}

	public void denyLoan(Loan loan) {
		loan.denyLoan();

		requestedLoans.remove(loan);

		bank.saveData();
	}
	
	public void requestLoan(Loan loan) {
		requestedLoans.add(loan);
	}

	public ArrayList<Loan> getRequestedLoans() {
		return this.requestedLoans;
	}
}
