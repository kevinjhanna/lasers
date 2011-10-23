package frontend;
import java.awt.EventQueue;


public class LasersAndMirrors {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GameController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
