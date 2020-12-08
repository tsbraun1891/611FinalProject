
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GUIQuit {
	private JFrame frame;	
	public GUIQuit() {
		frame = new JFrame();
		addBackgroundPic();
		frame.setSize(800,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addBackgroundPic() {
		ImageIcon icon = new ImageIcon("image/byebye.jpg");
		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
	}
	public void closeFrame() {
		frame.dispose();
	}
}
