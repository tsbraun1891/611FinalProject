import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class IO {
    private Bank bank;

    public IO(Bank bank) {
        this.bank = bank;
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
     * Appends the given currency to the specified file (file is created if does not exist already)
     * @param fileName - name of the currency csv file
     * @param currency - the currency to be written out
     */
    public void writeCurrencyToFile(String fileName, Currency currency) {
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

        ArrayList<String> currencyAttributes = new ArrayList<>();

        currencyAttributes.add(currency.getName());
        currencyAttributes.add(currency.getDesc());
        currencyAttributes.add(currency.getSymbol());
        currencyAttributes.add(String.valueOf(currency.getExchangeRate()));

        linesToWrite.add(currencyAttributes);

        /* Then we just turn our currency into a list of strings and call writeFile */
        this.writeManyToFile(fileName, linesToWrite);
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

}
