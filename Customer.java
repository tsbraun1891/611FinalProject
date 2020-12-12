import java.util.ArrayList;

public class Customer extends User{
	private ArrayList<Loan> loans;

	public Customer(int userId,String firstName, String lastName, String userName, String password, double balance, Currency currency) {
		super(userId, firstName, lastName, userName, password, balance, currency);
		loans = new ArrayList<>();
	}
	
	public void addNewLoan(Loan loan) {
		loans.add(loan);
	}

	public ArrayList<Loan> getLoans() {
		return this.loans;
	}	
	
}
