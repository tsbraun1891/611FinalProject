
public class LoanPending extends Loan{

	public LoanPending(User owner, User loaner, Currency currency, double balance, double interestRate) {
		super(owner, loaner, currency, balance, interestRate);
	}

}
