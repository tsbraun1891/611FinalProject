
public class Bank {

	private double balance;
	
	
	public double getBalance() {
		return this.balance;
	}
	
	public void addToBalance(double num) {
		this.balance +=num;
	}
	
	public void removeBalance(double num) {
		this.balance -= num;
	}
}
