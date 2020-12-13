import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Bank {
    IO bankIO;
    public ArrayList<Currency> currencies;
    public ArrayList<User> users;
    public ArrayList<Account> accounts;

    ArrayList<Loan> loans;

    private final String userFile = "./data/Users.csv";
    private final String oldUserFile = "./data/Users_old.csv";
    private final String accountFile = "./data/Accounts.csv";
    private final String oldAccountFile = "./data/Accounts_old.csv";
    private final String loanFile = "./data/Loans.csv";
    private final String oldLoanFile = "./data/Loans_old.csv";

    private User currentUser;

    /* Opening/closing accounts and making transactions with a checking account incur a 1% fee */
    private final double standardFee = .01;
    /* Customers must have at least 10k in the bank to gain interest */
    private final double standardInterestThreshold = 10000;
    /* The interest rate for savings accounts will be .5% */
    private final double standardSavingsInterest = .005;
    
    public Bank() {
        bankIO = new IO(this);
        currencies = this.setCurrencies();


        users = bankIO.readUsers(userFile);

        accounts = bankIO.readAccounts(accountFile);
        loans = bankIO.readLoans(loanFile);

        System.out.println(loans.get(0).getOwner().firstName);
        System.out.println(loans.get(0).getLoaner().firstName);
        System.out.println(loans.get(0).getBalance());
    }

    /**
     * Initialize the preset different currency types
     * @return an array list of the available currencies at this bank
     */
    private ArrayList<Currency> setCurrencies() {
        ArrayList<Currency> rhet = new ArrayList<>();

        rhet.add(new Currency("US Dollar", "USD", "$", 1.00));
        rhet.add(new Currency("Euro", "EUR", "€", 1.21));
        rhet.add(new Currency("Yen", "YEN", "¥", .0096));

        return rhet;
    }

    /**
     * Finds all of the accounts for a given user
     * @param user - the user who's accounts you want to get
     * @return a list of their accounts
     */
    public ArrayList<Account> getAllAccounts(User user) {
        ArrayList<Account> rhet = new ArrayList<>();

        for(Account account : accounts) {
            if(account.getOwner().equals(user)) {
                rhet.add(account);
            }
        }

        return rhet;
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
     * @param firstName
     * @param lastName
     * @param username - unique username of the account
     * @param password
     * @param startingBalance - their starting wallet size
     * @param currency - the currency they are currently working with
     * @return whether the username was unique or not
     */
    public boolean registerNewUser(String firstName, String lastName, String username, String password, double startingBalance, Currency currency) {

        if(this.getUserByUsername(username) != null) {
            return false;
        }

        User newUser = new User(0, firstName, lastName, username, password, startingBalance, currency);

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
        Account account;

        switch(type) {
        case SAVING:
            account = new Savings(owner, currencyType, startingAmount, standardFee, standardInterestThreshold, standardSavingsInterest);
            break;

        case CHECKING:
            account = new Checking(owner, currencyType, startingAmount, standardFee);
            break;

        default:
            return false;
        }

        /* Now we subtract the opening account fee */
        return account.openAccount();
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


        /* Now write all of the current data out */
        bankIO.writeUsersToFile(userFile, users);
        bankIO.writeAccountsToFile(accountFile, accounts);
        bankIO.writeLoansToFile(loanFile, loans);

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
        return user.addToBalance(amount);
    }

    /**
     * Subtracts amount from user's wallet in user's current currency
     * @param user - the user to subtract money from
     * @param amount - how much money to subtract
     * @return the new balance of the user's wallet
     */
    public double takeMoneyFromWallet(User user, double amount) {
        return user.subtractFromBalance(amount);
    }

    /**
     * Represents the user taking their money out of the ATM
     * @param user
     */
    public void cashOut(User user) {
        user.subtractFromBalance(user.getBalance());
    }


    // TODO: Create transaction ability
        // Transactions will go from a specified account to a user's wallet
    // TODO: Edit transaction class

    // TODO: create request loan ability

    // TODO: Pass one month

<<<<<<< Updated upstream
=======
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

        return rhet;
    }

    /**
     * @param user
     * @return a list of all transactions of money sent to this user
     */
    public ArrayList<Transaction> getTransactionsForUser(User user) {
        ArrayList<Transaction> rhet = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getSender().equals(user))
                rhet.add(t);
            else if(t.getReceiver().equals(user))
                rhet.add(t);
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
    
>>>>>>> Stashed changes
}
