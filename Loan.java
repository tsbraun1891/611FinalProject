public class Loan extends BalanceHandler {
	protected User owner;
	protected User loaner;
	private double rate;
	private boolean approved, denied;
	private int loanID;
	private Bank bank;

	/**
     * Create a new loan account
     * @param owner - the user that took out this loan
	 * @param loaner - the user that receives the payoff of this loan
     * @param currency - the type of the currency this loan was given in
     * @param balance - the current amount of that currency on this loan
	 * @param interestRate - the rate of interest that this account can generate
     */
	public Loan(int loanID, User owner, User loaner, Currency currency, double balance, double interestRate, Bank b) {
		super(balance, currency);

		this.owner = owner;
		this.loaner = loaner;
		this.rate = interestRate;
		this.approved = false;
		this.denied = false;
		this.loanID = loanID;
		this.bank = b;
	}

	/* Same as the other constructor except you can specify whether a loan is approved already or not */
	public Loan(int loanID, User owner, User loaner, Currency currency, double balance, double interestRate, Bank b, boolean approved) {
		super(balance, currency);

		this.owner = owner;
		this.loaner = loaner;
		this.rate = interestRate;
		this.approved = approved;
		this.denied = false;
		this.loanID = loanID;
		this.bank = b;
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
		if(!this.approved) {
			/* Change the flag */
			this.approved = true;

			/* Add the loan amount to the requester's wallet */
			this.owner.addToBalance(this.balance, this.currency);
			this.loaner.subtractFromBalance(this.balance, this.currency);
		}
		
	}

	public void denyLoan() {
		this.denied = true;
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

	/**
	 * pays off a portion of this loan
	 * @param amount - amount to pay off (in source's currency)
	 * @param source - where the amount is coming from
	 * @return the resulting loan balance
	 */
	public double payOffLoanAmount(double amount, BalanceHandler source) {
		if(source.getBalance() >= amount) {
			double paid;
			Currency paidCurrency;
			if(!this.paidOff()) {
				if(source.getCurrencyType().convertToCurrency(this.balance) < amount) {
					paid = this.balance;
					paidCurrency = this.currency;
				} else {
					paid = amount;
					paidCurrency = source.getCurrencyType();
				}	
				
				this.subtractFromBalance(paid, paidCurrency);
				source.subtractFromBalance(paid, paidCurrency);
				this.loaner.addToBalance(paid, paidCurrency);
			

				/* Create transaction from source to this loan */
				int newID = (int) (Math.random() * 1000000);
				for(Transaction xact : bank.transactions) {
					if(newID == xact.getID())
						newID = (int) (Math.random() * 1000000);
				}

				bank.transactions.add(new Transaction(newID, source, this, paid, paidCurrency));

				/* Create transaction from loan to loaner */
				newID = (int) (Math.random() * 1000000);
				for(Transaction xact : bank.transactions) {
					if(newID == xact.getID())
						newID = (int) (Math.random() * 1000000);
				}

				bank.transactions.add(new Transaction(newID, this, this.loaner, paid, paidCurrency));
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
