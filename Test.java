public class Test {
    
    public static void main(String[] args) {
        Bank bank = new Bank();

        for(User c : bank.users) {
            System.out.println(c.getClass());
            System.out.println(c.firstName);
            System.out.println(c.lastName);
            System.out.println(c.balance);
            System.out.println(c.currency);
        }

        bank.bankIO.writeCurrenciesToFile("./data/Currencies2.csv", bank.currencies);
    }
}
