package frontend;

import game.Controller;
import gui.BoardPanel;
import gui.BoardPanelListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -7317507468993791993L;
	private static final int CELL_SIZE = 30;
	private Controller controller;
	private final int boardWidth;
	private final int boardHeight;
	private BoardPanel boardPanel;
	private JLabel scoreLabel;

	public GamePanel(Controller controller, int boardHeight, int boardWidth) {
		this.controller = controller;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		initialize();
	}

	public void initialize() {
		setLayout(new BorderLayout());
		initializeBoard();
		initializeScore();
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


	private void initializeScore() {
		scoreLabel = new JLabel();
		scoreLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		add(scoreLabel, BorderLayout.SOUTH);
	}

	public void setCellImage(int row, int column, Image image) {
		boardPanel.setImage(row, column, image);
		boardPanel.repaint();
	}

	public void updateScore(int score) {
		scoreLabel.setText("Score: " + score);
	}
}
