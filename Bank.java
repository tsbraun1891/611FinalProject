import java.util.ArrayList;

public class Bank {
    IO bankIO;
    ArrayList<Currency> currencies;
    ArrayList<User> users;
    ArrayList<Account> accounts;

    private final String currencyFile = "./data/Currencies.csv";
    private final String userFile = "./data/Users.csv";
    
    public Bank() {
        bankIO = new IO(this);
        currencies = bankIO.readCurrencies(currencyFile);
        users = bankIO.readUsers(userFile);
        accounts = new ArrayList<>();
    }


}
