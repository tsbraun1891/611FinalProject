import java.util.ArrayList;

public class Bank {
    IO bankIO;
    ArrayList<Currency> currencies;
    ArrayList<User> users;
    ArrayList<Account> accounts;

    ArrayList<Loan> loans;

    private final String userFile = "./data/Users.csv";
    private final String oldUserFile = "./data/Users_old.csv";
    private final String accountFile = "./data/Accounts.csv";
    private final String oldAccountFile = "./data/Accounts_old.csv";
    private final String loanFile = "./data/Loans.csv";
    private final String oldLoanFile = "./data/Loans_old.csv";

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
    public void addNewUserToSystem(User user) {
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

}
