import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Bank {
    IO bankIO;
    public ArrayList<Currency> currencies;
    public ArrayList<User> users;
    public ArrayList<Account> accounts;
    public ArrayList<Transaction> transactions;
    public ArrayList<Loan> loans;

    private final String userFile = "./data/Users.csv";
    private final String oldUserFile = "./data/Users_old.csv";
    private final String accountFile = "./data/Accounts.csv";
    private final String oldAccountFile = "./data/Accounts_old.csv";
    private final String loanFile = "./data/Loans.csv";
    private final String oldLoanFile = "./data/Loans_old.csv";
    private final String transactionFile = "./data/Transactions.csv";
    private final String oldTransactionFile = "./data/Transactions_old.csv";

    private User currentUser;
    /* One bank manager */
    private Admin admin;
    
    private static Bank bank = null;

    /* Opening/closing accounts and making transactions with a checking account incur a 1% fee */
    private final double standardFee = .01;
    /* Customers must have at least 10k in the bank to gain interest */
    private final double standardInterestThreshold = 10000;
    /* The interest rate for savings accounts will be .5% */
    private final double standardSavingsInterest = .005;
    /* Standard Loan rate */
    private final double standardLoanInterest = .05;
    
    private Bank() {
        bankIO = new IO(this);
        currencies = this.setCurrencies();


        users = bankIO.readUsers(userFile);
        for(User user : users) {
            if(user instanceof Admin)
                admin = (Admin) user;
        }

        accounts = bankIO.readAccounts(accountFile);
        for(Account account : accounts) {
            account.getOwner().addNewAccount(account);
        }

        loans = bankIO.readLoans(loanFile);
        for(Loan loan : loans) {
            /* Only customers can take out loans */
            ((Customer) loan.getOwner()).addNewLoan(loan);
            /* Loans that are read were not denied (since denied loans aren't written),
            so they are only still pending if they are not approved */
            if(!loan.isApproved())
                admin.requestLoan(loan);
        }

        transactions = bankIO.readTransactions(transactionFile);
    }
    
    /**
     * singleton Bank instance
     * @return
     */
    public static Bank getInstance() {
        if (bank == null)
            bank = new Bank();
        return bank;
    }

    /**
     * Write all of the currently stored data out to the disk
     * First renames all of the previous data to temp files in case of crash,
     * then writes out all of the new info
     * @return
     */
    public void saveData() {
        /* Rename all of the files to have backups in case of crash */
        bankIO.renameFile(userFile, oldUserFile);
        bankIO.renameFile(accountFile, oldAccountFile);
        bankIO.renameFile(loanFile, oldLoanFile);
        bankIO.renameFile(transactionFile, oldTransactionFile);


        /* Now write all of the current data out */
        bankIO.writeUsersToFile(userFile, users);
        bankIO.writeAccountsToFile(accountFile, accounts);

        /* Only write non-paid off loans and non-denied loans */
        ArrayList<Loan> loansToWrite = new ArrayList<>();
        for(Loan loan : loans) {
            if(!loan.paidOff() && !loan.isDenied())
                loansToWrite.add(loan);
        }
        bankIO.writeLoansToFile(loanFile, loansToWrite);

        bankIO.writeTransactionsToFile(transactionFile, transactions);
    }

    /**
     * Initialize the preset different currency types
     * @return an array list of the available currencies at this bank
     */
    private ArrayList<Currency> setCurrencies() {
        ArrayList<Currency> rhet = new ArrayList<>();

        rhet.add(new Currency("US Dollar", "USD", "$", 1.00));
        rhet.add(new Currency("Euro", "EUR", "€", 0.83));
        rhet.add(new Currency("Yen", "YEN", "¥", 104.01));

        return rhet;
    }

    /**
     * Finds all of the accounts for a given user
     * @param user - the user who's accounts you want to get
     * @return a list of their accounts
     */
    public ArrayList<Account> getAllAccounts(User user) {
        return user.getAccounts();
    }

    /**
     * Adds a new user to the system and make sure 
     * its userID is unique
     * @param user
     */
    private void addNewUserToSystem(User user) {
        for(User u : users) {
            if(u.getUserId() == user.getUserId()) {
                user.setUserId( (int) (Math.random() * 10000) );
            }
        }

        this.users.add(user);

        ArrayList<User> toWrite = new ArrayList<>();
        toWrite.add(user);

        bankIO.writeUsersToFile(userFile, toWrite);
    }

    /**
     * Create a new User object, add it to our database, and set them as the current user
     * @param newAdmin - whether or not this new user should be an admin
     * @param firstName
     * @param lastName
     * @param username - unique username of the account
     * @param password
     * @param startingBalance - their starting wallet size
     * @param currency - the currency they are currently working with
     * @return whether the username was unique or not
     */
    public boolean registerNewUser(boolean newAdmin, String firstName, String lastName, String username, String password, double startingBalance, Currency currency) {

        if(this.getUserByUsername(username) != null) {
            return false;
        }

        User newUser;

        if(newAdmin) {
            newUser = new Admin(0, firstName, lastName, username, password, startingBalance, currency, this);
        } else {
            newUser = new Customer(0, firstName, lastName, username, password, startingBalance, currency);
        }
        

        this.addNewUserToSystem(newUser);
        this.currentUser = newUser;

        return true;
    }



    /**
     * Create a new account of the given type and subtract the service fee from the account balance
     * @param type - type of the account
     * @param owner - the user who is creating this account
     * @param currencyType - the currency this account will be created in
     * @param startingAmount - the amount of money in the given currency type to start the account with
     * @return whether the account was successfully created or not
     */
    public boolean createNewAccount(AccountType type, User owner, Currency currencyType, double startingAmount) {
        if(startingAmount <= owner.getBalance()) {
            Account account;
            int newID = (int) (Math.random() * 1000000);

            for(Account a : accounts) {
                if(newID == a.getID()) {
                    newID = (int) (Math.random() * 1000000);
                }
            }

            switch(type) {
            case SAVING:
                account = new Savings(newID, owner, currencyType, startingAmount, standardFee, standardInterestThreshold, standardSavingsInterest);
                break;

            case CHECKING:
                account = new Checking(newID, owner, currencyType, startingAmount, standardFee);
                break;

            default:
                return false;
            }

            /* Now we subtract the opening account fee */
            owner.addNewAccount(account);
            accounts.add(account);

            this.saveData();

            return account.openAccount();
        } else {
            return false;
        }
        
    }
    

    /**
     * Gives back the User object given their username
     * @param username - the username you are searching for
     * @return the user associated with that username (null if username DNE)
     */
    public User getUserByUsername(String username) {
        for(User user : users) {
            if(user.getUserName().equals(username)) {
                return user;
            }
        }

        /* User does not exist */
        return null;
    }

    /**
     * @return the current active user
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    /**
     * Checks login credentials and sets current user on successful login
     * @param username
     * @param password
     * @return whether or not the login was successful
     */
    public boolean login(String username, String password) {
        User user = this.getUserByUsername(username);

        if(user != null && password.equals(user.getPassword())) {
            this.currentUser = user;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds amount to user's wallet in user's current currency
     * @param user - the user to add money to
     * @param amount - how much money to add
     * @return the new balance of the user's wallet
     */
    public double addMoneyToWallet(User user, double amount) {
        user.addToBalance(amount);
        this.saveData();

        return user.getBalance();
    }

    /**
     * Subtracts amount from user's wallet in user's current currency
     * @param user - the user to subtract money from
     * @param amount - how much money to subtract
     * @return the new balance of the user's wallet
     */
    public double takeMoneyFromWallet(User user, double amount) {
        user.subtractFromBalance(amount);
        this.saveData();

        return user.getBalance();
    }

    /**
     * Represents the user taking their money out of the ATM
     * @param user
     */
    public void cashOut(User user) {
        user.subtractFromBalance(user.getBalance());

        this.saveData();
    }
    
    /**
     * Withdraws money from a specified account into the owner's wallet
     * @param user - the user withdrawing the money
     * @param account - the account being withdrawn from
     * @param amount - the amount of money to withdraw (given in account currency)
     * @return whether the user was able to withdraw or not
     */
    public boolean withdrawFromAccount(User user, Account account, double amount) {
    	if(amount<0) {
    		return false;
        }
        
        if(account.getOwner().equals(user)) {
            if(amount > account.getBalance()) {
                return false;
            }

            account.withdrawFromAccount(amount);
        }
        
        int newID = (int) (Math.random() * 1000000);
        for(Transaction xact : transactions) {
            if(newID == xact.getID())
                newID = (int) (Math.random() * 1000000);
        }

        this.transactions.add(new Transaction(newID, user, account, amount, account.getCurrencyType()));
        
        this.saveData();

    	return true;
    }
    /**
     * deposit money from a user's wallet to an account
     * @param user - the user making the deposit
     * @param account - the account being deposited to
     * @param amount - how much money to deposit (given in user's currency type)
     * @return
     */
    public boolean depositToAccount(User user, Account account, double amount) {
    	if(amount < 0) {
    		return false;
    	}
    	
    	if(amount > user.getBalance()) {
    		return false;
    	}
        
        if(amount <= user.getBalance()) {
            account.addToBalance(amount, user.getCurrencyType());
            user.subtractFromBalance(amount);
        }

        int newID = (int) (Math.random() * 1000000);
        for(Transaction xact : transactions) {
            if(newID == xact.getID())
                newID = (int) (Math.random() * 1000000);
        }

        this.transactions.add(new Transaction(newID, user, account, amount, user.getCurrencyType()));

        this.saveData();

    	return true;
    }
    
    /**
     * transfer money between two users, two accounts, or one of each
     * @param sender - The object sending the money
     * @param receiver - The object receiving the money
     * @param amount - how much money to send (in sender's currency)
     * @return whether the transaction went through or not
     */
    public boolean transferMoney(BalanceHandler sender, BalanceHandler receiver, double amount ) {
    	if(amount < 0) {
    		return false;
    	}
    
    	if(amount> sender.getBalance()) {
    		return false;
    	}
    	sender.transferMoneyToOther(receiver, amount);

        int newID = (int) (Math.random() * 1000000);
        for(Transaction xact : transactions) {
            if(newID == xact.getID())
                newID = (int) (Math.random() * 1000000);
        }

        this.transactions.add(new Transaction(newID, sender, receiver, amount, sender.getCurrencyType()));

        this.saveData();

    	return true;
    }


    public void requestLoan(Customer owner, Currency currencyType, double amount) {
        int newID = (int) (Math.random() * 1000000);

        for(Loan l : loans) {
            if(newID == l.getID()) {
                newID = (int) (Math.random() * 1000000);
            }
        }

        Loan newLoan = new Loan(newID, owner, admin, currencyType, amount, this.standardLoanInterest, this);

        owner.addNewLoan(newLoan);
        loans.add(newLoan);
        admin.requestLoan(newLoan);

        this.saveData();
    }

    public void passOneMonth() {
        for(Account a : accounts) {
            if(a instanceof Savings)
                ((Savings) a).passOneMonth();
        }

        for(Loan l : loans) {
            if(l.isApproved())
                l.passOneMonth();
        }

        this.transactions = new ArrayList<>();

        this.saveData();
    }

    /**
     * @param account
     * @return a list of all transactions involving account
     */
    public ArrayList<Transaction> getTransactionsForAccount(Account account) {
        ArrayList<Transaction> rhet = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getSender().equals(account))
                rhet.add(t);
            else if(t.getReceiver().equals(account))
                rhet.add(t);
        }

        rhet = this.removeDuplicatesFromArray(rhet);

        return rhet;
    }

    /**
     * @param laon
     * @return a list of all transactions involving laon
     */
    public ArrayList<Transaction> getTransactionsForLoan(Loan loan) {
        ArrayList<Transaction> rhet = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getSender().equals(loan))
                rhet.add(t);
            else if(t.getReceiver().equals(loan))
                rhet.add(t);
        }

        rhet = this.removeDuplicatesFromArray(rhet);

        return rhet;
    }

    /**
     * @param user
     * @return a list of all transactions involving this user or any of their accounts or loans
     */
    public ArrayList<Transaction> getTransactionsForUser(User user) {
        ArrayList<Transaction> rhet = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getSender().equals(user))
            	if(!rhet.contains(t)) {
                	rhet.add(t);
            	}
            else if(t.getReceiver().equals(user))
            	if(!rhet.contains(t)) {
                	rhet.add(t);
            	}
        }

        for(Account a : user.getAccounts()) {
            rhet.addAll(getTransactionsForAccount(a));
        }

        for(Loan l : loans) {
            if(l.getOwner().equals(user)) {
                rhet.addAll(this.getTransactionsForLoan(l));
            }
        }

        
        rhet = this.removeDuplicatesFromArray(rhet);


        return rhet;
    }

    /**
     * @param user
     * @return a list of all transactions involving this users wallet
     */
    public ArrayList<Transaction> getTransactionsForWallet(User user) {
        ArrayList<Transaction> rhet = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getSender().equals(user))
            	if(!rhet.contains(t)) {
                	rhet.add(t);
            	}
            else if(t.getReceiver().equals(user))
            	if(!rhet.contains(t)) {
                	rhet.add(t);
            	}
        }
        
        rhet = this.removeDuplicatesFromArray(rhet);


        return rhet;
    }

    private <T> ArrayList<T> removeDuplicatesFromArray(ArrayList<T> array) {
        ArrayList<T> rhet = new ArrayList<>();

        for(T item : array) {
            if(!rhet.contains(item)) {
                rhet.add(item);
            }
        }

        return rhet;
    }
    
    public ArrayList<Currency> getCurrencyTypes(){
    	return currencies;
    }
    
    /**
     * @return a list of all transactions that accrued daily
     */
    public ArrayList<Transaction> getDailyReport(){
    	ArrayList<Transaction> trans = new ArrayList<>();
    	
    	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        for(Transaction t: transactions) {
        	if(t.getDate().equals(date)) {
        		trans.add(t);
        	}
        }
        return trans;
    }  
}
