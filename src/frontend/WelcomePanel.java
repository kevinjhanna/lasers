package frontend;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 3633909978988836502L;
	private Controller controller;
	private JButton newGame;
	private JButton loadGame;
	private JButton quit;
	
	public WelcomePanel(Controller controller) {
		this.controller = controller;
		initialize();
	}
	
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

			@Override
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

			@Override
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

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.quit();
			}

		});
		add(quit);
	}
}
