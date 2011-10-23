package frontend;
import java.awt.EventQueue;

public class LasersAndMirrors {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GameController(new Window());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
