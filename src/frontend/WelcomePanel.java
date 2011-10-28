package frontend;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel that presents a menu with program options if a game has not yet been
 * loaded.
 */
public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Panel controller
	 */
	private Controller controller;

	/**
	 * New Game button
	 */
	private JButton newGame;

	/**
	 * Load Game button
	 */
	private JButton loadGame;

	/**
	 * Quit button
	 */
	private JButton quit;

	/**
	 * Creates a new Welcome panel that responds to the controller parameter
	 * 
	 * @param controller
	 *            The controller that will respond to this panel
	 */
	public WelcomePanel(Controller controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Initializes the welcome panel. This method was created in order to clean
	 * the constructor.
	 */
	public void initialize() {
		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		setLayout(null);

		// Title label
		JLabel title = new JLabel("Lasers & Mirrors");
		title.setFont(new Font("Helvetica", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(117, 28, 200, 16);
		add(title);

		// New game button
		newGame = new JButton("New game");
		newGame.putClientProperty("JButton.buttonType", "gradient");
		newGame.setBounds(117, 73, 200, 40);
		newGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.newGame();
			}

		});
		add(newGame);

		// Load game button
		loadGame = new JButton("Load game");
		loadGame.putClientProperty("JButton.buttonType", "gradient");
		loadGame.setBounds(117, 115, 200, 40);
		loadGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.loadGame();
			}

		});
		add(loadGame);

		// Quit game button
		quit = new JButton("Quit");
		quit.putClientProperty("JButton.buttonType", "gradient");
		quit.setBounds(117, 157, 200, 40);
		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.quit();
			}

		});
		add(quit);
	}
}
