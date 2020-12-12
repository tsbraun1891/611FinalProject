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
     * This function transfers money from this account to another user
     * and then charges a fee as this is a checking account
     * @param otherUser - the user you are transferring money to
     * @param amount - the amount you are transferring
     * @return the resulting balance of this account
     */
    public double transferMoneyToUser(User otherUser, double amount) {
        if(this.balance >= amount) {
            super.newFeeTransaction(amount);
            super.transferMoneyToUser(otherUser, amount);
        }

        return this.balance;
    }
}
