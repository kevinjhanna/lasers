package frontend;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

/**
 * The application menu which is a component of <tt>Window</tt>.
 * 
 * @see Window
 */
public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	/**
	 * The view controller
	 */
	private Controller controller;

	/**
	 * File menu
	 */
	private JMenu file;

	private JMenuItem fileNew;
	private JMenuItem fileLoad;
	private JMenuItem fileSave;
	private JMenuItem fileClose;
	private JMenuItem fileQuit;

	/**
	 * Creates a new menu
	 * 
	 * @param controller
	 *            The controller to dispatch actions to
	 */
	public Menu(final Controller controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Initializes every element of the menu
	 */
	private void initialize() {
		int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		file = new JMenu("File");

		// File -> New game...
		fileNew = new JMenuItem("New game...");
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, mask));
		fileNew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.newGame();
			}

		});
		file.add(fileNew);

		// File -> Open saved game...
		fileLoad = new JMenuItem("Load saved game...");
		fileLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, mask));
		fileLoad.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.loadGame();
			}

		});
		file.add(fileLoad);

		file.add(new JSeparator());

		// File -> Save
		fileSave = new JMenuItem("Save");
		fileSave.setEnabled(false);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, mask));
		fileSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.saveGame();
			}

		});
		file.add(fileSave);

		// File -> Close
		fileClose = new JMenuItem("Close");
		fileClose.setEnabled(false);
		fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, mask));
		fileClose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.closeGame();
			}

		});
		file.add(fileClose);

		file.add(new JSeparator());

		fileQuit = new JMenuItem("Quit");
		fileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, mask));
		fileQuit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.quit();
			}

		});
		file.add(fileQuit);

		add(file);
	}

	public void enableGameItems(boolean b) {
		fileSave.setEnabled(b);
		fileClose.setEnabled(b);
	}
}
