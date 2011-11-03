package frontend;

import gui.ImageUtils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Panel that presents a menu with program options if a game has not yet been
 * loaded.
 */
public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Panel controller.
	 */
	private Controller controller;

	/**
	 * New Game button.
	 */
	private JButton newGame;

	/**
	 * Load Game button.
	 */
	private JButton loadGame;

	/**
	 * Quit button.
	 */
	private JButton quit;

	/**
	 * Background image
	 */
	private Image background;

	/**
	 * Creates a new Welcome panel that responds to the controller parameter.
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

		// Load background image
		try {
			background = ImageUtils.loadImage("resources/title.png");
		} catch (IOException e) {
			JOptionPane
					.showMessageDialog(
							this,
							"Unable to load all resources. You may continue to play the game, but some images may not show.",
							"Resource error", JOptionPane.WARNING_MESSAGE);
		}

		// New game button
		newGame = new JButton("New game");
		newGame.putClientProperty("JButton.buttonType", "gradient");
		newGame.setBounds(50, 122, 200, 40);
		newGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.newGame();
			}

		});
		add(newGame);

		// Load game button
		loadGame = new JButton("Load game");
		loadGame.putClientProperty("JButton.buttonType", "gradient");
		loadGame.setBounds(50, 173, 200, 40);
		loadGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.loadGame();
			}

		});
		add(loadGame);

		// Quit game button
		quit = new JButton("Quit");
		quit.putClientProperty("JButton.buttonType", "gradient");
		quit.setBounds(50, 223, 200, 40);
		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.quit();
			}

		});
		add(quit);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (background != null) {
			g.drawImage(background, 0, 0, null);
		}
	}
}
