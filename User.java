import java.util.ArrayList;

public abstract class User extends BalanceHandler {

	protected int userId;
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String password;
	protected ArrayList<Account> accounts;
    

	public User(int userId, String firstName, String lastName, String username, String password, double balance, Currency currency) {
        super(balance, currency);
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(username);
		setPassword(password);

		accounts = new ArrayList<>();
	}
	
	//getter and setter for user
	public void setUserId(int id) {
		this.userId = id;
	}
	public int getUserId() {
		return this.userId;
	}
	
	public void setFirstName(String first) {
		this.firstName = first;
	}
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String last) {
		this.lastName = last;
	}
	public String getLastName() {
		return this.lastName;
	}
	
	public void setUserName(String user) {
		this.userName = user;
	}
	public String getUserName() {
		return this.userName;
	}
	
	public void setPassword(String psw) {
		this.password = psw;
	}
	public String getPassword() {
		return this.password;
  }

	public boolean equals(Object other) {
		if(other instanceof User) {
			User o = (User) other;
			if(o.getUserId() == this.getUserId()) 
				return true;
		}

		return false;
	}

	private boolean isCustomerAccount(Account account){
        return accounts.contains(account);
	}

	public ArrayList<Account> getAccounts() {
		return this.accounts;
	}

	public void addNewAccount(Account account) {
		accounts.add(account);
	}
	
}
