
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIHome h = new GUIHome();

		Bank b = Bank.getInstance();

		b.saveData();
	}

}
