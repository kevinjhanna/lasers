import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.*;

public class MainWindow {

	private JFrame frame;
	private Game game;

	/**
	 * Lanza la aplicaci—n
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicaci—n
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Inicializa el contenido del frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Lasers & Mirrors");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());
		initializeMenuBar();
	}
	
	private void initializeMenuBar() {
		
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");

		JMenuItem fileOpen = new JMenuItem("Open");
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();

				int ret = c.showOpenDialog(frame);
				if (ret == JFileChooser.APPROVE_OPTION) {
					File f = c.getSelectedFile();
					game = new Game(f);
				}
			}

		});
		file.add(fileOpen);

		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.setEnabled(false);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();

				int ret = c.showSaveDialog(frame);
				if (ret == JFileChooser.APPROVE_OPTION) {
					File f = c.getSelectedFile();
					game.save(f);
				}
			}

		});
		file.add(fileSave);

		JMenuItem fileQuit = new JMenuItem("Quit");
		fileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		file.add(fileQuit);
		menubar.add(file);

		frame.setJMenuBar(menubar);
	}

}
