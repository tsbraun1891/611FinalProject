import java.util.ArrayList;

public class Bank {
    IO bankIO;
    ArrayList<Currency> currencies;
    ArrayList<User> users;
    ArrayList<Account> accounts;

    private final String userFile = "./data/Users.csv";
    
    public Bank() {
        bankIO = new IO(this);
        currencies = this.setCurrencies();


        users = bankIO.readUsers(userFile);
        accounts = new ArrayList<>();
    }

    /**
     * Initialize the preset different currency types
     * @return an array list of the available currencies at this bank
     */
    private ArrayList<Currency> setCurrencies() {
        ArrayList<Currency> rhet = new ArrayList<>();

        rhet.add(new Currency("US Dollar", "USD", "$", 1.00));
        rhet.add(new Currency("Euro", "EUR", "€", 1.21));

        return rhet;
    }


}
