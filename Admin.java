public class Admin extends User {

	private AdminAccount account;
	
	public Admin(int userId, String fistName, String lastName, String username, String password, double balance, Currency currency) {
		super(userId, fistName, lastName, username, password, balance, currency);
	}
    
	
	public void approveLoan(Customer customer, LoanPending pendingLoan){
        if (account.getBalance() >= pendingLoan.getBalance()){
            customer.getAllLoan().add(new Loan(pendingLoan));
            customer.getPendingLoan().remove(pendingLoan);
            account.addToBalance(pendingLoan.getBalance());
        } else {
        	denyLoan(customer, pendingLoan);
        }
    }
	
	public void denyLoan(Customer customer, LoanPending pendingLoan) {
		customer.getPendingLoan().remove(pendingLoan);
	}
}
