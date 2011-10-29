package frontend;

import exceptions.GameIOException;
import exceptions.InvalidBoardFileException;
import exceptions.InvalidBoardSizeException;
import exceptions.NoGameException;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;
import game.Game;
import game.Observer;
import iogame.IOSerializer;

import java.io.File;
import java.io.IOException;

import parser.GameParser;

import tiles.Drawable;

/**
 * Implementation of the <tt>Controller</tt> in the MVC architecture.
 * 
 * @see Controller  
 */
public class GameController implements Controller, Observer {

	private Game game;
	private ViewContainer container;

	/**
	 * GameController constructor
	 */
	public GameController() {
		initialize();
	}
	
	/**
	 * Initializes the view
	 */
	private void initialize() {
		container = new Window(this);
	}

	/**
	 * Closes the current game
	 */
	public void closeGame() {
		if (game != null) {
			ConfirmOption opt = container.showConfirm("Close without save?");

			if (opt == ConfirmOption.NO) {
				saveGame();
			}
			if (opt == ConfirmOption.CANCEL) {
				return;
			}
			game = null;
		}
		container.setGameVisible(false);
	}

	/**
	 * Loads a game from a previously saved game.
	 */
	public void loadGame() {
		File f = container.showLoad();
		if (f != null) {
			try {
				game = IOSerializer.load(f);
				startGame();
			} catch (GameIOException e) {
				container.showError("Unable to load saved game.");
			}
		}
	}

	/**
	 * Moves a tile in the game.
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 */
	public void move(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn) {

		if (!game.isFixed(sourceRow, sourceColumn)) {
			try {
				game.move(sourceRow, sourceColumn, targetRow, targetColumn);
			} catch (SourceTileEmptyException e) {
				// Empty catch on purpose. Do nothing
			} catch (TargetTileNotEmptyException e) {
				// Empty catch on purpose. Do nothing
			}
		}
	}

	/**
	 * Creates a new game from a board file.
	 */
	public void newGame() {
		File f = container.showNew();
		if (f != null) {
			try {
				GameParser parser = new GameParser(f);
				game = parser.parse();
				startGame();
			} catch (IOException e) {
				container.showError("Unable to load board file.");
			} catch (InvalidBoardFileException e) {
				String message = "Error while trying to load board file. The board file has been corrupted.";
				if (e.getMessage() != null) {
					message += ": " + e.getMessage();
				}
				container.showError(message);
			} catch (InvalidBoardSizeException e) {
				container.showError("The board size is too big.");
			}
		}
	}

	/**
	 * Updates the score in the view when it changes
	 * 
	 * @param newScore
	 */
	public void onScoreChange(int newScore) {
		container.getView().updateScore(newScore);
	}

	/**
	 * Updates the cell in the view when a tile has been moved
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */
	public void onTileUpdate(int row, int column, Drawable drawable) {
		container.getView().updateCell(row, column, drawable);
	}

	/**
	 * Quits the program. If there is a game in progress, asks the user whether
	 * he/she wants to save it
	 */
	public void quit() {
		if (game != null) {
			ConfirmOption opt = container.showConfirm("Quit without save?");

			if (opt == ConfirmOption.NO) {
				saveGame();
			}
			if (opt == ConfirmOption.CANCEL) {
				return;
			}
		}
		System.exit(0);
	}

	/**
	 * Rotates a tile in the game
	 * 
	 * @param row
	 * @param column
	 */
	public void rotate(int row, int column) {
		if (game.canRotate(row, column)) {
			game.rotate(row, column);
		}
	}

	/**
	 * Saves the current game
	 */
	public void saveGame() {

		if (game == null) {
			throw new NoGameException();
		}

		File f = container.showSave();
		if (f != null) {
			try {
				IOSerializer.save(game, f);
			} catch (GameIOException e) {
				container.showError("Unable to save game.");
			}
		}
	}

	/**
	 * Starts the game
	 */
	private void startGame() {
		container.setGame(game.getBoardHeight(), game.getBoardWidth());
		container.setGameVisible(true);
		game.start(this);
	}

	/**
	 * Shows win message
	 */
	public void onWin() {
		container.showWin();
		closeGame();
	}
}
