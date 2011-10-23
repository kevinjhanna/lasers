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
	@Override
	public void setGame(int boardHeight, int boardWidth) {
		gamePanel = new GamePanel(controller, boardHeight, boardWidth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see frontend.ViewContainer#setGameVisible(boolean)
	 */
	@Override
	public void setGameVisible(boolean b) {
		if (b) {
			getContentPane().remove(welcomePanel);
			getContentPane().add(gamePanel);
			setSize(gamePanel.getWidth(), menu.getHeight() + gamePanel.getHeight());
			menu.enableGameItems(true);
		} else {
			getContentPane().remove(gamePanel);
			getContentPane().add(welcomePanel);
			setBounds(100, 100, 450, 300);
			menu.enableGameItems(false);
		}
	}

	public View getView() {
		if (gamePanel == null) {
			throw new NoGameException();
		}
		return gamePanel;
	}

	@Override
	public void showWarning(String message) {
		JOptionPane.showMessageDialog(this, message, "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
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

	@Override
	public File showSave() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("save"));

		int ret = fc.showSaveDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}

	@Override
	public File showLoad(String extensionFilter) {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter(extensionFilter));

		int ret = fc.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
}
