import java.util.ArrayList;

public class Admin extends User {
	ArrayList<Loan> requestedLoans;

	public Admin(int userId, String fistName, String lastName, String username, String password, double balance, Currency currency) {
		super(userId, fistName, lastName, username, password, balance, currency);

		requestedLoans = new ArrayList<>();
	}

	public void approveLoan(Loan loan) {
		loan.approveLoan();
		requestedLoans.remove(loan);
	}

	public void denyLoan(Loan loan) {
		loan.denyLoan();
		requestedLoans.remove(loan);
	}
	
	public void requestLoan(Loan loan) {
		requestedLoans.add(loan);
	}
}
