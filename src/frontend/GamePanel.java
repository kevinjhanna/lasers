package frontend;

import gui.BoardPanel;
import gui.BoardPanelListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tiles.Drawable;

/**
 * Implementation of the View in the MVC architecture
 */
public class GamePanel extends JPanel implements View {

	private static final long serialVersionUID = -7317507468993791993L;

	private static final int CELL_SIZE = 30;

	private Controller controller;
	private final int boardWidth;
	private final int boardHeight;
	private ImageDrawer drawer = new ImageDrawer();
	private BoardPanel boardPanel;
	private JPanel statusPanel;
	private JLabel scoreLabel;
	private JLabel timeLabel;
	private String scoreFormat = "<html><b>Score:</b> %s</html>";
	private String timeFormat = "<html><b>Elapsed time:</b> %s</html>";
	private String timeFormatSmall = "%s";
	private boolean useSmallTimeFormat = false;

	private int elapsedTime;

	/**
	 * Intantiates a new GamePanel
	 * 
	 * @param controller
	 *            The controller that will respond for this panel
	 * @param boardHeight
	 *            The height of the game board
	 * @param boardWidth
	 *            The width of the game board
	 */
	public GamePanel(Controller controller, int boardHeight, int boardWidth) {
		this.controller = controller;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		initialize();
	}

	/**
	 * Initializes the panel components
	 */
	public void initialize() {
		setLayout(new BorderLayout());
		initializeBoard();
		initializeStatusPanel();
		setSize(boardPanel.getWidth() + 20, boardPanel.getHeight() + 68);
	}

	/**
	 * Initializes the panel board
	 */
	private void initializeBoard() {
		boardPanel = new BoardPanel(boardHeight, boardWidth, CELL_SIZE);
		boardPanel.setBackground(Color.WHITE);
		boardPanel.setGridColor(Color.GRAY);
		boardPanel.setListener(new BoardPanelListener() {

			public void cellClicked(int row, int column) {
				controller.rotate(row, column);
			}

			public void cellDragged(int sourceRow, int sourceColumn,
					int targetRow, int targetColumn) {

				controller.move(sourceRow, sourceColumn, targetRow,
						targetColumn);
			}

		});
		add(boardPanel);
	}

	/**
	 * Initializes the status panel, which contains the current score and an
	 * elapsed time counter
	 */
	private void initializeStatusPanel() {
		statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout());
		statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		initializeScore();
		initializeTimer();
		add(statusPanel, BorderLayout.SOUTH);
	}

	/**
	 * Initializes the score tracker
	 */
	private void initializeScore() {
		scoreLabel = new JLabel();
		statusPanel.add(scoreLabel, BorderLayout.WEST);
	}

	private void initializeTimer() {
		// If board is too small, use reduced label
		if (boardWidth < 8) {
			useSmallTimeFormat = true;
		}

		elapsedTime = 0;
		timeLabel = new JLabel(formatTime(elapsedTime));

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeLabel.setText(formatTime(++elapsedTime));
			}
		});
		timer.setRepeats(true);
		timer.start();

		statusPanel.add(timeLabel, BorderLayout.EAST);
	}

	private String formatTime(int elapsedTime) {
		int minutes = elapsedTime / 60;
		int seconds = elapsedTime % 60;
		String time = String.format("%02d:%02d", minutes, seconds);
		return String.format(useSmallTimeFormat ? timeFormatSmall : timeFormat,
				time);
	}

	public void updateCell(int row, int column, Drawable drawable) {
		boardPanel.clearImage(row, column);
		for (Image layer : drawer.draw(drawable)) {
			boardPanel.appendImage(row, column, layer);
		}
		boardPanel.repaint();
	}

	public void clearCell(int row, int column) {
		boardPanel.clearImage(row, column);
		boardPanel.repaint();
	}

	public void updateScore(int score) {
		scoreLabel.setText(String.format(scoreFormat, score));
	}
}
