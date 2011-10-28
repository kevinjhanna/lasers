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
	private JLabel elapsedTimeLabel;
	private String scoreFormat = "<html><b>Score:</b> %s</html>";
	private String elapsedTimeFormat = "<html><b>Elapsed time:</b> %s</html>";
	private int elapsedTime;

	public GamePanel(Controller controller, int boardHeight, int boardWidth) {
		this.controller = controller;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		initialize();
	}

	public void initialize() {
		setLayout(new BorderLayout());
		initializeBoard();
		initializeStatusPanel();
		setSize(boardPanel.getWidth() + 20, boardPanel.getHeight() + 68);
	}

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

	private void initializeStatusPanel() {
		statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout());
		statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		initializeScore();
		initializeTimer();
		add(statusPanel, BorderLayout.SOUTH);
	}

	private void initializeScore() {
		scoreLabel = new JLabel();
		statusPanel.add(scoreLabel, BorderLayout.WEST);
	}

	private void initializeTimer() {
		elapsedTime = 0;
		
		elapsedTimeLabel = new JLabel(String.format(elapsedTimeFormat, formatTime(elapsedTime)));

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elapsedTimeLabel.setText(String.format(elapsedTimeFormat, formatTime(++elapsedTime)));
			}
		});
		timer.setRepeats(true);
		timer.start();
		statusPanel.add(elapsedTimeLabel, BorderLayout.EAST);
	}

	private String formatTime(int elapsedTime) {
		int minutes = elapsedTime / 60;
		int seconds = elapsedTime % 60;
		return String.format("%02d:%02d", minutes, seconds);
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
		scoreLabel.setText(String.format(scoreFormat , score));
	}
}
