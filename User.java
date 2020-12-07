
public class User extends BalanceHandler {

	protected int userId;
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String password;
    

	public User(int userId, String firstName, String lastName, String username, String password, double balance, Currency currency) {
        super(balance, currency);
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(username);
		setPassword(password);
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
	
}
