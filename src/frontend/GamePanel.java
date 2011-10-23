package frontend;

import gui.BoardPanel;
import gui.BoardPanelListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tiles.Tile;

public class GamePanel extends JPanel implements View {

	private static final long serialVersionUID = -7317507468993791993L;
	private static final int CELL_SIZE = 30;
	private Controller controller;
	private final int boardWidth;
	private final int boardHeight;
	private BoardPanel boardPanel;
	private JPanel statusPanel;
	private JLabel scoreLabel;
	private JLabel timerLabel;
	private int elapsedTime;
	private ImageTileDrawer tileDrawer = new ImageTileDrawer();

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
		setSize(boardPanel.getWidth() + 20, boardPanel.getHeight() + 63);
	}

	private void initializeBoard() {
		boardPanel = new BoardPanel(boardHeight, boardWidth, CELL_SIZE);
		boardPanel.setBackground(Color.WHITE);
		boardPanel.setGridColor(Color.GRAY);
		boardPanel.setListener(new BoardPanelListener() {

			@Override
			public void cellClicked(int row, int column) {
				controller.rotate(row, column);
			}

			@Override
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
		statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
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
		timerLabel = new JLabel(formatTime(elapsedTime));

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timerLabel.setText(formatTime(++elapsedTime));
			}
		});
		timer.setRepeats(true);
		timer.start();
		statusPanel.add(timerLabel, BorderLayout.EAST);
	}

	private String formatTime(int elapsedTime) {
		int minutes = elapsedTime / 60;
		int seconds = elapsedTime % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	@Override
	public void setCellImage(int row, int column, Tile tile) {
		if (tile == null) {
			boardPanel.clearImage(row, column);
		} else {
			boardPanel.setImage(row, column, tile.draw(tileDrawer));
			boardPanel.repaint();
		}
	}

	@Override
	public void updateScore(int score) {
		scoreLabel.setText("Score: " + score);
	}
}
