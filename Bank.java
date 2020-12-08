import java.util.ArrayList;

public class Bank {
    IO bankIO;
    ArrayList<Currency> currencies;
    ArrayList<User> users;
    ArrayList<Account> accounts;

    private final String userFile = "./data/Users.csv";
    private final String oldUserFile = "./data/Users_old.csv";
    private final String accountFile = "./data/Accounts.csv";
    private final String oldaccountFile = "./data/Accounts_old.csv";
    
    public Bank() {
        bankIO = new IO(this);
        currencies = this.setCurrencies();


        users = bankIO.readUsers(userFile);
        accounts = bankIO.readAccounts(accountFile);
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
     * Write all of the currently stored data out to the disk
     * First renames all of the previous data to temp files in case of crash,
     * then writes out all of the new info
     * @return
     */
    public void saveData() {
        /* Rename all of the files to have backups in case of crash */
        bankIO.renameFile(userFile, oldUserFile);
        bankIO.renameFile(accountFile, oldaccountFile);


        /* Now write all of the current data out */
        bankIO.writeUsersToFile(userFile, users);
        /* Only save accounts that are open */
        ArrayList<Account> toWrite = new ArrayList<>();
        for(Account a : accounts) {
            if(a.isOpen) {
                toWrite.add(a);
            }
        }
        bankIO.writeAccountsToFile(accountFile, toWrite);
    }

    public void addNewUserToSystem(User user) {
        this.users.add(user);

        ArrayList<User> toWrite = new ArrayList<>();
        toWrite.add(user);

        bankIO.writeUsersToFile(userFile, toWrite);
    }



}
