package game;

import java.io.Serializable;
import java.util.List;

import misc.Pair;
import misc.Position;
import tiles.Source;
import tiles.Target;
import tiles.Tile;
import exceptions.InvalidBoardSizeException;
import exceptions.RotationNotSupportedException;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;

/**
 * The class that models a Lasers and Mirrors game. Implementation of the model
 * in the MVC architecture.
 * 
 * This class handles the business logic.
 * 
 */
public class Game implements Serializable {

	private static final long serialVersionUID = 3199525037781602721L;

	private transient Observer observer;
	private Integer score;
	private Board board;
	public static final int MIN_HEIGHT = 5;
	public static final int MIN_WIDTH = 5;
	public static final int MAX_HEIGHT = 20;
	public static final int MAX_WIDTH = 20;

	/**
	 * Creates a new game with a board of the dimensions given and a set of
	 * initial tiles. Does not populate the board with the tiles to enable the
	 * setup of an observer to track board changes
	 * 
	 * @param boardHeight
	 * @param boardWidth
	 * @param tiles
	 * @throws InvalidBoardSizeException
	 */
	public Game(int boardHeight, int boardWidth,
			List<Pair<Tile, Position>> tiles) throws InvalidBoardSizeException {

		if (!validSize(boardHeight, boardWidth)) {
			throw new InvalidBoardSizeException();
		}

		board = new Board(boardHeight, boardWidth);
		populateBoard(tiles);
	}

	/**
	 * Starts the game.
	 */
	public void start(Observer observer) {
		if (observer == null) {
			throw new IllegalArgumentException("Observer can't be null.");
		}
		this.observer = observer;
		calculateRays();
	}

	/**
	 * Check if the given size properties are valid for this game.
	 * 
	 * @param height
	 * @param width
	 * @return boolean
	 */
	public static boolean validSize(int height, int width) {
		return (height >= MIN_HEIGHT && height <= MAX_HEIGHT
				&& width >= MIN_WIDTH && width <= MAX_WIDTH);
	}

	/**
	 * Returns the width of the game board.
	 * 
	 * @return Integer
	 */
	public Integer getBoardWidth() {
		return board.getWidth();
	}

	/**
	 * Returns the height of the game board.
	 * 
	 * @return Integer
	 */
	public Integer getBoardHeight() {
		return board.getHeight();
	}

	/**
	 * Returns the current game score.
	 * 
	 * @return Integer
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * Returns true if the tile at the specified position supports rotation.
	 * 
	 * @param row
	 * @param column
	 * @return boolean
	 */
	public boolean canRotate(int row, int column) {
		return getTile(row, column).canRotate();
	}

	/**
	 * Returns the tile located at the given row and column
	 * 
	 * @param row
	 * @param column
	 * @return tile
	 */
	private Tile getTile(int row, int column) {
		return board.getTile(new Position(row, column));
	}

	/**
	 * Moves a tile from the source position to the target position
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 * @throws SourceTileEmptyException
	 * @throws TargetTileNotEmptyException
	 */
	public void move(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn) {

		Position source = new Position(sourceRow, sourceColumn);
		Position target = new Position(targetRow, targetColumn);
		board.moveTile(source, target);

		calculateRays();
	}

	/**
	 * Rotates the tile at the given position
	 * 
	 * @param row
	 * @param column
	 * @throws RotationNotSupportedException
	 */
	public void rotate(int row, int column)
			throws RotationNotSupportedException {

		board.getTile(new Position(row, column)).rotate();

		calculateRays();
	}

	/**
	 * Updates the score and notifies the observer.
	 */
	private void updateScore() {
		Integer old = score;
		score = calculateScore();

		if (old != score) {
			observer.onScoreChange(score);
		}

		verifyWinCondition();
	}

	/**
	 * Recalculates game score.
	 * 
	 * @return int
	 */
	private int calculateScore() {
		int score = 0;

		for (int i = 0; i < getBoardHeight(); i++) {
			for (int j = 0; j < getBoardWidth(); j++) {
				if (getTile(i, j).hasBeams()) {
					score++;
				}
			}
		}
		return score;
	}

	/**
	 * Populates the board with the current set of tiles and their corresponding
	 * locations.
	 * 
	 * @param tiles
	 */
	private void populateBoard(List<Pair<Tile, Position>> tiles) {
		for (Pair<Tile, Position> p : tiles) {
			if (p != null) {
				board.setTile(p.getSecond(), p.getFirst());
			}
		}
	}

	/**
	 * Propagates rays across the board and updates the score.
	 */
	private void calculateRays() {
		clearBoardRays();

		for (int i = 0; i < getBoardHeight(); i++) {
			for (int j = 0; j < getBoardWidth(); j++) {
				Tile tile = getTile(i, j);
				if (tile instanceof Source) {
					new Ray(tile.getColor(), tile.getDirection()).propagate(
							board, new Position(i, j));
				}
			}
		}

		for (int i = 0; i < getBoardHeight(); i++) {
			for (int j = 0; j < getBoardWidth(); j++) {
				observer.onCellUpdate(i, j, getTile(i, j));
			}
		}

		updateScore();
	}

	/**
	 * Checks if the game has been won.
	 */
	private void verifyWinCondition() {
		boolean win = true;

		for (int i = 0; i < getBoardHeight(); i++) {
			for (int j = 0; j < getBoardWidth(); j++) {
				Tile tile = getTile(i, j);
				if (tile instanceof Target && !tile.hasBeam(tile.getColor())) {
					win = false;
				}
			}
		}

		if (win) {
			observer.onWin();
		}
	}

	/**
	 * Clear board rays. This method should run before every ray calculation.
	 */
	private void clearBoardRays() {
		for (int i = 0; i < getBoardHeight(); i++) {
			for (int j = 0; j < getBoardWidth(); j++) {
				getTile(i, j).clearRays();
			}
		}
	}

	/**
	 * Returns whether the tile at the specified position is fixed.
	 * 
	 * @param row
	 * @param column
	 * @return boolean
	 */
	public boolean isFixed(int row, int column) {
		return getTile(row, column).isFixed();
	}

	/**
	 * Returns whether the tile at the specified position is empty.
	 * 
	 * @param row
	 * @param column
	 * @return boolean
	 */
	public boolean isEmpty(int row, int column) {
		return getTile(row, column).isEmpty();
	}

}
