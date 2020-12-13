public class Checking extends Account {
    
    /**
     * Create a new account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     * @param feeRate - The percentage removed in a fee from each transaction
     */
    public Checking(int accountID, User owner, Currency currency, double balance, double feeRate) {
        super(accountID, owner, currency, balance, feeRate);        
    }

    /**
     * This function transfers money from this account to another balance
     * and then charges a fee as this is a checking account
     * @param otherUser - the balance you are transferring money to
     * @param amount - the amount you are transferring
     * @return the resulting balance of this account
     */
    public double transferMoneyToOther(BalanceHandler other, double amount) {
        if(this.balance >= amount) {
            super.newFeeTransaction(amount);
            super.transferMoneyToOther(other, amount);
        }

        return this.balance;
    }
    
    public String toString() {
    	String s = "Checking - ";
        String rhet = String.valueOf(this.accountID);
        rhet = rhet.substring(rhet.length()-4, rhet.length());

        return s+rhet;
    }
}
