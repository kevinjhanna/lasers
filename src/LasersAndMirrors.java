import game.Game;
import game.SourceTileEmptyException;
import game.TargetTileNotEmptyException;
import gui.BoardPanel;
import gui.BoardPanelListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import tiles.RotationNotSupportedException;

public class LasersAndMirrors extends JFrame {

	private static final long serialVersionUID = -6172792314391695505L;
	private static final int CELL_SIZE = 30;
	private GameObserver gameObserver;
	private Game game;
	private JPanel contentPane;
	private JPanel welcomePanel;
	private JLabel scoreLabel;
	private JMenuItem fileClose;
	private BoardPanel boardPanel;
	public ImageFactory imageFactory = ImageFactory.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LasersAndMirrors frame = new LasersAndMirrors();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private LasersAndMirrors() {
		setTitle("Lasers & Mirrors");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);

		initializeMenuBar();
		initializeContentPane();
		initializeWelcomePanel();
		welcomePanel.setVisible(true);
	}

	private void initializeContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private void initializeScore() {
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		scoreLabel.setVisible(false);

		contentPane.add(scoreLabel, BorderLayout.SOUTH);
	}

	private void initializeBoard() {
		boardPanel = new BoardPanel(game.getBoardHeight(),
				game.getBoardHeight(), CELL_SIZE);
		boardPanel.setBackground(Color.WHITE);
		boardPanel.setGridColor(Color.GRAY);
		boardPanel.setListener(new BoardPanelListener() {

			public void cellClicked(int row, int column) {
				try {
					game.rotate(row, column);
					boardPanel.repaint();
				} catch (RotationNotSupportedException e) {
					JOptionPane.showMessageDialog(LasersAndMirrors.this,
							"Can't rotate tile.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}

			public void cellDragged(int sourceRow, int sourceColumn,
					int targetRow, int targetColumn) {

				try {
					game.move(sourceRow, sourceColumn, targetRow, targetColumn);
				} catch (SourceTileEmptyException e) {
					JOptionPane.showMessageDialog(LasersAndMirrors.this,
							"Source tile is empty.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} catch (TargetTileNotEmptyException e) {
					JOptionPane.showMessageDialog(LasersAndMirrors.this,
							"Target tile is not empty.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		boardPanel.setVisible(false);

		contentPane.add(boardPanel);

	}

	/**
	 * Inicializa el panel de bienvenida
	 */
	private void initializeWelcomePanel() {
		welcomePanel = new JPanel();
		welcomePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		welcomePanel.setLayout(null);

		JButton newGame = new JButton("New game");
		newGame.setBounds(117, 61, 200, 30);
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createNewGame();
			}

		});
		welcomePanel.add(newGame);

		JButton openGame = new JButton("Open game");
		openGame.setBounds(117, 103, 200, 30);
		openGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openSavedGame();
			}

		});
		welcomePanel.add(openGame);

		JButton exit = new JButton("Exit");
		exit.setBounds(117, 145, 200, 30);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}

		});
		welcomePanel.add(exit);

		contentPane.add(welcomePanel);
	}

	/**
	 * Corrige las dimensiones de la ventana para adaptarlas al tama–o del
	 * tablero
	 */
	private void autoResize() {
		setSize(boardPanel.getWidth() + 20, boardPanel.getHeight() + 63);
	}

	/**
	 * Inicializa la barra de menu
	 */
	private void initializeMenuBar() {
		if (System.getProperty("os.name").equals("Mac OS X")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}

		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");

		JMenuItem fileNew = new JMenuItem("New game...");
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createNewGame();
			}

		});
		file.add(fileNew);

		JMenuItem fileOpen = new JMenuItem("Open saved game...");
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openSavedGame();
			}

		});
		file.add(fileOpen);

		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.setEnabled(false);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}

		});
		file.add(fileSave);

		fileClose = new JMenuItem("Close");
		fileClose.setEnabled(false);
		fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close(false);
			}

		});
		file.add(fileClose);

		JMenuItem fileQuit = new JMenuItem("Quit");
		fileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		fileQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}

		});
		file.add(fileQuit);
		menubar.add(file);

		setJMenuBar(menubar);
	}

	/**
	 * Retorna el BoardPanel asociado a la ventana
	 * 
	 * @return BoardPanel
	 */
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	private void saveGame() {
		if (game == null) {
			throw new GameNotFoundException();
		}

		JFileChooser c = new JFileChooser();

		int ret = c.showSaveDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File f = c.getSelectedFile();
			game.save(f);
		}
	}

	private void createNewGame() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("board"));

		int ret = fc.showOpenDialog(LasersAndMirrors.this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();

			try {
				initializeScore();
				gameObserver = new GameObserver(this);
				game = Game.fromBoardFile(f, gameObserver);
				initializeBoard();
				game.start();
				setGameVisible(true);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, e.toString(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void setGameVisible(boolean b) {
		boardPanel.setVisible(b);
		scoreLabel.setVisible(b);
		fileClose.setEnabled(b);
		welcomePanel.setVisible(!b);

		autoResize();
	}

	private void close(boolean exitAfter) {
		if (game != null) {
			int ret = JOptionPane.showConfirmDialog(this,
					"Close without save?", "Close",
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (ret == JOptionPane.NO_OPTION) {
				saveGame();
			}
			if (ret == JOptionPane.CANCEL_OPTION) {
				return;
			}

			setGameVisible(false);
			game = null;
			gameObserver = null;
			this.remove(boardPanel);
			boardPanel = null;
			this.remove(scoreLabel);
			scoreLabel = null;
		}
	}

	private void quit() {
		if (game != null) {
			int ret = JOptionPane.showConfirmDialog(this, "Quit without save?",
					"Quit", JOptionPane.YES_NO_CANCEL_OPTION);

			if (ret == JOptionPane.NO_OPTION) {
				saveGame();
			}
			if (ret == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		System.exit(0);
	}

	private void openSavedGame() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("save"));

		int ret = fc.showOpenDialog(LasersAndMirrors.this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			initializeBoard();
			initializeScore();

			try {
				gameObserver = new GameObserver(this);
				game = Game.fromSaveFile(f, gameObserver);
				setGameVisible(true);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, e.toString(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void setScore(int score) {
		scoreLabel.setText("Score: " + score);
	}
}
