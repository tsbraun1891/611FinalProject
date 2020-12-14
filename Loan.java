public class Loan extends BalanceHandler {
	protected User owner;
	protected User loaner;
	private double rate;
	private boolean approved, denied;
	private int loanID;

	/**
     * Create a new loan account
     * @param owner - the user that took out this loan
	 * @param loaner - the user that receives the payoff of this loan
     * @param currency - the type of the currency this loan was given in
     * @param balance - the current amount of that currency on this loan
	 * @param interestRate - the rate of interest that this account can generate
     */
	public Loan(int loanID, User owner, User loaner, Currency currency, double balance, double interestRate) {
		super(balance, currency);

		this.owner = owner;
		this.loaner = loaner;
		this.rate = interestRate;
		this.approved = false;
		this.denied = false;
		this.loanID = loanID;
	}

	/* Same as the other constructor except you can specify whether a loan is approved already or not */
	public Loan(int loanID, User owner, User loaner, Currency currency, double balance, double interestRate, boolean approved) {
		super(balance, currency);

		this.loanID = loanID;
		this.owner = owner;
		this.loaner = loaner;
		this.rate = interestRate;
		this.approved = approved;
		this.denied = false;
	}

	public User getOwner() {
        return owner;
	}
	
	public boolean paidOff() {
		return this.balance <= 0;
	}

    /**
     * Change the owner of this loan to a different user
     * @param newOwner - the new user to set as the owner of this loan
     */
    public void setOwner(User newOwner) {
        this.owner = newOwner;
	}
	
	public User getLoaner() {
        return loaner;
	}
	
	public double getInterestRate() {
		return this.rate;
	}

	public void setInterestRate(double newRate) {
		this.rate = newRate;
	}

	public boolean isApproved() {
		return this.approved;
	}

	public boolean isDenied() {
		return this.denied;
	}

	public void approveLoan() {
		this.approved = true;
	}

	public void denyLoan() {
		this.approved = true;
	}

	public int getID() {
		return this.loanID;
	}

    /**
     * Change the loaner of this loan to a different user
     * @param newLoaner - the new user to set as the loaner of this loan
     */
    public void setLoaner(User newLoaner) {
        this.loaner = newLoaner;
	}

	// TODO: Add in subtracting from user wallet / checks if they can afford
	public double payOffLoanAmount(double amount) {
		if(!this.paidOff()) {
			if(this.balance < amount) {
				this.subtractFromBalance(this.balance);
				this.loaner.addToBalance(this.balance, this.currency);
			} else {
				this.subtractFromBalance(amount);
				this.loaner.addToBalance(amount, this.currency);
			}			
		}		

		return this.balance;
	}

	/**
     * Function to call at the end of every month. Interest is generated at
     * the end of every month.
     * @return the balance after a month passes
     */
    public double passOneMonth() {
		if(!this.paidOff()) {
			this.addToBalance(this.balance * this.rate);
		}        

        return this.balance;
	}
	
	public boolean equals(Object other) {
        if(other instanceof Loan) {
            Loan otherLoan = (Loan) other;
            if(otherLoan.getID() == this.getID()) {
                return true;
            }
        }

        return false;
    }
	
	 public String toString() {
	    	String s = "Loan - ";
	        String rhet = String.valueOf(this.loanID);
	        rhet = rhet.substring(rhet.length()-4, rhet.length());

	        return s+rhet;
	    }
}
