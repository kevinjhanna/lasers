package frontend;

import game.Controller;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private static final long serialVersionUID = 3894510086087199629L;
	private Controller controller;
	private Menu menu;
	private WelcomePanel welcomePanel;
	private GamePanel gamePanel;
	
	public Window(Controller controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		setTitle("Lasers & Mirrors");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		initializeContentPane();
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		menu = new Menu(controller);
		setJMenuBar(menu);
	}
	
	private void initializeContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		welcomePanel = new WelcomePanel(controller);
		contentPane.add(welcomePanel);
	}
	
	public void setGame(int boardHeight, int boardWidth) {
		gamePanel = new GamePanel(controller, boardHeight, boardWidth);
	}
	
	public void setGameVisible(boolean b) {
		if (b) {
			getContentPane().remove(welcomePanel);
			getContentPane().add(gamePanel);
			setSize(gamePanel.getSize());
			menu.enableGameItems(true);
		} else {
			getContentPane().remove(gamePanel);
			getContentPane().add(welcomePanel);
			setBounds(100, 100, 450, 300);
			menu.enableGameItems(false);
		}
	}
	
	public GamePanel getGamePanel() {
		if (gamePanel == null) {
			throw new NoGameException();
		}
		return gamePanel;
	}
}
