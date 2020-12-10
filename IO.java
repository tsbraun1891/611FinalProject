import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

// TODO: Add in approval to the loans

public class IO {
    private Bank bank;

    public IO(Bank bank) {
        this.bank = bank;
    }

    /**
     * Delete a given file
     * @param fileName - name of the file you want to delete
     * @return whether or not the file was sucessfully deleted;
     */
    private boolean deleteFile(String fileName) {
        File file = new File(fileName);

        return file.delete();
    }

    /**
     * Renames the given file name with the new name
     * Deletes newFileName if it already exists
     * @param fileName
     * @param newFileName
     * @return whether or not the rename was successful
     */
    public boolean renameFile(String fileName, String newFileName) {
        File oldFile = new File(fileName);
        File newFile = new File(newFileName);

        if(newFile.exists())
            this.deleteFile(newFileName);

        return oldFile.renameTo(newFile);
    }

    /**
     * Writes multiple rows of data out to specified csv file
     * @param fileName - Name of the file to write to
     * @param linesToWrite - ArrayList of rows to write out to the file
     */
    private void writeManyToFile(String fileName, ArrayList<ArrayList<String>> linesToWrite) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for(ArrayList<String> row : linesToWrite) {
                writer.write(String.join(",", row));
                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            System.err.println(e);
        }       
    }

    /**
     * Appends the given currencies to the specified file (file is created if does not exist already)
     * @param fileName - name of the currency csv file
     * @param currencies - list of the currencies to be written out
     */
    public void writeCurrenciesToFile(String fileName, ArrayList<Currency> currencies) {
        ArrayList<ArrayList<String>> linesToWrite = new ArrayList<>();

        /* If the currency file does not exist, we have to add the headers to our list*/
        File check = new File(fileName);
        if(!check.isFile()) {
            ArrayList<String> headers = new ArrayList<>();

            headers.add("Name");
            headers.add("Desc");
            headers.add("Symbol");
            headers.add("ExchangeRate");

            linesToWrite.add(headers);
        }
      
        for(Currency currency : currencies) {
            ArrayList<String> currencyAttributes = new ArrayList<>();

            currencyAttributes.add(currency.getName());
            currencyAttributes.add(currency.getDesc());
            currencyAttributes.add(currency.getSymbol());
            currencyAttributes.add(String.valueOf(currency.getExchangeRate()));

            linesToWrite.add(currencyAttributes);
        }

        /* Then we just turn our currency into a list of strings and call writeFile */
        this.writeManyToFile(fileName, linesToWrite);
    }

    /**
     * Appends the given users to the specified file (file is created if does not exist already)
     * @param fileName - name of the user csv file
     * @param users - the users to be written out
     */
    public void writeUsersToFile(String fileName, ArrayList<User> users) {
        ArrayList<ArrayList<String>> linesToWrite = new ArrayList<>();

        /* If the currency file does not exist, we have to add the headers to our list*/
        File check = new File(fileName);
        if(!check.isFile()) {
            ArrayList<String> headers = new ArrayList<>();

            headers.add("Type");
            headers.add("UserID");
            headers.add("Firstname");
            headers.add("LastName");
            headers.add("Username");
            headers.add("Password");
            headers.add("Balance");
            headers.add("CurrencyDesc");

            linesToWrite.add(headers);
        }

        for(User user : users) {
            ArrayList<String> userAttributes = new ArrayList<>();
            
            if(user instanceof Admin) {
                userAttributes.add("Admin");
            } else {
                userAttributes.add("Customer");
            }
    
            userAttributes.add("" + user.getUserId());
            userAttributes.add(user.getFirstName());
            userAttributes.add(user.getLastName());
            userAttributes.add(user.getUserName());
            userAttributes.add(user.getPassword());
            userAttributes.add(String.valueOf(user.getBalance()));
            userAttributes.add(user.getCurrencyType().getDesc());
    
            linesToWrite.add(userAttributes);
        }

        /* Then we just turn our currency into a list of strings and call writeFile */
        this.writeManyToFile(fileName, linesToWrite);
    }

    /**
     * Appends the given accounts to the specified file (file is created if does not exist already)
     * @param fileName - name of the account csv file
     * @param accounts - the accounts to be written out
     */
    public void writeAccountsToFile(String fileName, ArrayList<Account> accounts) {
        ArrayList<ArrayList<String>> linesToWrite = new ArrayList<>();

        /* If the currency file does not exist, we have to add the headers to our list*/
        File check = new File(fileName);
        if(!check.isFile()) {
            ArrayList<String> headers = new ArrayList<>();
            headers.add("Type");
            headers.add("Owner");
            headers.add("CurrencyDesc");
            headers.add("Balance");
            headers.add("FeeRate");
            headers.add("IntThreshold");
            headers.add("IntRate");

            linesToWrite.add(headers);
        }

        for(Account account : accounts) {
            ArrayList<String> accountAttributes = new ArrayList<>();

            double threshold = 0, intRate = 0;
            
            if(account instanceof Savings) {
                accountAttributes.add("Savings");

                Savings s = (Savings) account;
                threshold = s.getThreshold();
                intRate = s.getInterestRate();
            } else {
                accountAttributes.add("Checking");
            }
    
            accountAttributes.add("" + account.getOwner().getUserId());
            accountAttributes.add(account.getCurrencyType().getDesc());
            accountAttributes.add(String.valueOf(account.getBalance()));
            accountAttributes.add(String.valueOf(account.getFeeRate()));
            accountAttributes.add(String.valueOf(threshold));
            accountAttributes.add(String.valueOf(intRate));
    
            linesToWrite.add(accountAttributes);
        }
        

        /* Then we just turn our currency into a list of strings and call writeFile */
        this.writeManyToFile(fileName, linesToWrite);
    }

    /**
     * Appends the given loans to the specified file (file is created if does not exist already)
     * @param fileName - name of the loan csv file
     * @param loans - the loans to be written out
     */
    public void writeLoansToFile(String fileName, ArrayList<Loan> loans) {
        ArrayList<ArrayList<String>> linesToWrite = new ArrayList<>();

        /* If the currency file does not exist, we have to add the headers to our list*/
        File check = new File(fileName);
        if(!check.isFile()) {
            ArrayList<String> headers = new ArrayList<>();

            headers.add("Owner");
            headers.add("Loaner");
            headers.add("CurrencyDesc");
            headers.add("Balance");
            headers.add("IntRate");

            linesToWrite.add(headers);
        }

        for(Loan loan : loans) {
            ArrayList<String> loanAttributes = new ArrayList<>();
    
            loanAttributes.add("" + loan.getOwner().getUserId());
            loanAttributes.add("" + loan.getLoaner().getUserId());
            loanAttributes.add(loan.getCurrencyType().getDesc());
            loanAttributes.add(String.valueOf(loan.getBalance()));
            loanAttributes.add(String.valueOf(loan.getInterestRate()));
    
            linesToWrite.add(loanAttributes);
        }

        /* Then we just turn our currency into a list of strings and call writeFile */
        this.writeManyToFile(fileName, linesToWrite);
    }

    /**
     * Reads a file and returns an array of all of the lines in the file
     * @param fileName - name of the file
     * @return string arraylist of all lines of the given file
     */
    public ArrayList<String> readFile(String fileName) {
        /* First check if file exists */
        File temp = new File(fileName);
        if(!temp.exists())
            return null;

        ArrayList<String> rhet = new ArrayList<String>();
        String row;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((row = reader.readLine()) != null) {
                rhet.add(row);
            }

            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        return rhet;
    }

    /**
     * Tries to read in currencies from the given csv file
     * @param fileName - name of the currency csv file
     * @return the list of currencies read in from the file
     */
    public ArrayList<Currency> readCurrencies(String fileName) {
        ArrayList<Currency> rhet = new ArrayList<>();

        ArrayList<String> output = readFile(fileName);
        
        /* We skip the first line since that is the labels line */
        for(int i = 1; i < output.size(); i++) {
            String[] attributes = output.get(i).split(",");

            rhet.add(new Currency(attributes[0], attributes[1], attributes[2], Double.parseDouble(attributes[3])));
        }

        return rhet;
    }

    /**
     * Tries to read in users from the given csv file
     * @param fileName - name of the user csv file
     * @return the list of users read in from the file
     */
    public ArrayList<User> readUsers(String fileName) {
        ArrayList<User> rhet = new ArrayList<>();

        ArrayList<String> output = readFile(fileName);
        
        /* We skip the first line since that is the labels line */
        for(int i = 1; i < output.size(); i++) {
            String[] attributes = output.get(i).split(",");

            Currency userCurrency = null;
            for(Currency c : this.bank.currencies) {
                if(c.getDesc().equals(attributes[7])) 
                    userCurrency = c;
            }

            if(attributes[0].equals("Admin")) {
                rhet.add(new Admin(Integer.parseInt(attributes[1]), attributes[2], attributes[3], attributes[4], attributes[5], Double.parseDouble(attributes[6]), userCurrency));
            } else {
                rhet.add(new Customer(Integer.parseInt(attributes[1]), attributes[2], attributes[3], attributes[4], attributes[5], Double.parseDouble(attributes[6]), userCurrency));
            }

        }

        return rhet;
    }

    /**
     * Tries to read in accounts from the given csv file
     * @param fileName - name of the account csv file
     * @return the list of accounts read in from the file
     */
    public ArrayList<Account> readAccounts(String fileName) {
        ArrayList<Account> rhet = new ArrayList<>();

        ArrayList<String> output = readFile(fileName);
        
        /* We skip the first line since that is the labels line */
        for(int i = 1; i < output.size(); i++) {
            String[] attributes = output.get(i).split(",");

            Currency accountCurrency = null;
            for(Currency c : this.bank.currencies) {
                if(c.getDesc().equals(attributes[2])) 
                    accountCurrency = c;
            }

            User accountOwner = null;
            for(User user : this.bank.users) {
                if(user.getUserId() == Integer.parseInt(attributes[1]))
                    accountOwner = user;
            }

            if(attributes[0].equals("Savings")) {
                rhet.add(new Savings(accountOwner, accountCurrency, Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]), Double.parseDouble(attributes[4]), Double.parseDouble(attributes[5])));
            } else {
                rhet.add(new Checking(accountOwner, accountCurrency, Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4])));
            }

        }

        return rhet;
    }

    /**
     * Tries to read in loans from the given csv file
     * @param fileName - name of the loan csv file
     * @return the list of loans read in from the file
     */
    public ArrayList<Loan> readLoans(String fileName) {
        ArrayList<Loan> rhet = new ArrayList<>();

        ArrayList<String> output = readFile(fileName);
        
        /* We skip the first line since that is the labels line */
        for(int i = 1; i < output.size(); i++) {
            String[] attributes = output.get(i).split(",");

            Currency loanCurrency = null;
            for(Currency c : this.bank.currencies) {
                if(c.getDesc().equals(attributes[2])) 
                loanCurrency = c;
            }

            User owner = null;
            User loaner = null;
            for(User u : this.bank.users) {
                if(u.getUserId() == Integer.parseInt(attributes[0]))
                    owner = u;
                else if(u.getUserId() == Integer.parseInt(attributes[1]))
                    loaner = u;
            }

            rhet.add(new Loan(owner, loaner, loanCurrency, Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4])));
        }

        return rhet;
    }

}
