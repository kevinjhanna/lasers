package frontend;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 3451850427555533566L;
	private Controller controller;
	private JMenu file;
	private JMenuItem fileNew;
	private JMenuItem fileOpen;
	private JMenuItem fileSave;
	private JMenuItem fileClose;
	private JMenuItem fileQuit;

	public Menu(final Controller controller) {
		this.controller = controller;
		initialize();
	}

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
		fileOpen = new JMenuItem("Open saved game...");
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, mask));
		fileOpen.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				controller.loadGame();
			}

		});
		file.add(fileOpen);

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
