package frontend;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends JFrame implements ViewContainer {

	private static final long serialVersionUID = 3894510086087199629L;
	private Controller controller;
	private Menu menu;
	private WelcomePanel welcomePanel;
	private GamePanel gamePanel;

	public void setController(Controller controller) {
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#initialize()
	 */
	public void initialize() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#setGame(int, int)
	 */
	public void setGame(int boardHeight, int boardWidth) {
		gamePanel = new GamePanel(controller, boardHeight, boardWidth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#setGameVisible(boolean)
	 */
	public void setGameVisible(boolean b) {
		if (b) {
			getContentPane().remove(welcomePanel);
			getContentPane().add(gamePanel);
			setSize(gamePanel.getWidth(),
					menu.getHeight() + gamePanel.getHeight());
			menu.enableGameItems(true);
		} else {
			getContentPane().remove(gamePanel);
			getContentPane().add(welcomePanel);
			setBounds(100, 100, 450, 300);
			menu.enableGameItems(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#getView()
	 */
	public View getView() {
		if (gamePanel == null) {
			throw new NoGameException();
		}
		return gamePanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#showWarning(message)
	 */
	public void showWarning(String message) {
		JOptionPane.showMessageDialog(this, message, "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#showError(message)
	 */
	public void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#showWinMessage()
	 */
	public void showWinMessage() {
		JOptionPane.showMessageDialog(this, "You have won!", "Win", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showConfirmDialog(this, "Restart game?", "Restart", JOptionPane.YES_NO_OPTION);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#showConfirm(message)
	 */
	public ConfirmOption showConfirm(String message) {
		int ret = JOptionPane.showConfirmDialog(this, message, "Confirm",
				JOptionPane.YES_NO_CANCEL_OPTION);

		if (ret == JOptionPane.YES_OPTION) {
			return ConfirmOption.YES;
		} else if (ret == JOptionPane.NO_OPTION) {
			return ConfirmOption.NO;
		} else {
			return ConfirmOption.CANCEL;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#showSave()
	 */
	public File showSave() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("board"));

		int ret = fc.showSaveDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#showLoad()
	 */
	public File showLoad() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("save"));

		int ret = fc.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#showNew()
	 */
	public File showNew() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("board"));

		int ret = fc.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
}
