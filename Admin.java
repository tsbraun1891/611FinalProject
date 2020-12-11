import java.util.ArrayList;

public class Admin extends User {
	private ArrayList<Loan> requestedLoans;
	
	public Admin(int userId, String fistName, String lastName, String username, String password, double balance, Currency currency) {
		super(userId, fistName, lastName, username, password, balance, currency);

		requestedLoans = new ArrayList<>();
	}

	public void approveLoan(Loan loan) {
		if(this.getBalance() >= loan.getBalance()) {
			loan.approveLoan();
			this.subtractFromBalance(loan.getBalance());
		}
		
		requestedLoans.remove(loan);
	}

	public void denyLoan(Loan loan) {
		loan.denyLoan();
		requestedLoans.remove(loan);
	}
	
	public void requestLoan(Loan loan) {
		requestedLoans.add(loan);
	}

	public ArrayList<Loan> getRequestedLoans() {
		return this.requestedLoans;
	}
}
