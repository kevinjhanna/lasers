package frontend;

import exceptions.RotationNotSupportedException;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;
import exceptions.TileIsFixedException;
import game.Game;
import game.Observer;

import java.io.File;
import java.io.IOException;

import tiles.Drawable;

/**
 * Main game controller. Acts as connector between de the view (which lives in a
 * container) and the game logic
 */
public class GameController implements Controller, Observer {

	private Game game;
	private ViewContainer container = new Window();

	/**
	 * GameController constructor
	 * 
	 * @param container
	 */
	public GameController() {
		container.setController(this);
		container.initialize();
		container.setVisible(true);
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
			container.setGameVisible(false);
		}
	}

	/**
	 * Loads a game from a previously saved game
	 */
	public void loadGame() {
		File f = container.showLoad();
		if (f != null) {
			try {
				game = Game.fromSaveFile(f);
				startGame();
			} catch (IOException e) {
				container.showError("Unable to load saved game.");
			}
		}
	}

	/**
	 * Moves a tile in the game
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 */
	public void move(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn) {

		try {
			game.move(sourceRow, sourceColumn, targetRow, targetColumn);
		} catch (SourceTileEmptyException e) {
			// Empty catch on purpose. Do nothing
		} catch (TargetTileNotEmptyException e) {
			// Empty catch on purpose. Do nothing
		} catch (TileIsFixedException e) {
			// Empty catch on purpose. Do nothing
		}
	}

	/**
	 * Crea un juego nuevo a partir de un archivo de tablero
	 */
	public void newGame() {
		File f = container.showNew();
		if (f != null) {
			try {
				game = Game.fromBoardFile(f);
				startGame();
			} catch (IOException e) {
				container.showError("Unable to load board file.");
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
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 * @param drawable
	 */
	public void onTileMove(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn, Drawable drawable) {

		container.getView().updateCell(sourceRow, sourceColumn, null);
		container.getView().updateCell(targetRow, targetColumn, drawable);
	}

	/**
	 * Updates the cell in the view when a tile has been rotated
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */

	public void onTileRotated(int row, int column, Drawable drawable) {
		container.getView().updateCell(row, column, drawable);
	}

	/**
	 * Updates the cell in the view when a tile has been set
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */
	public void onTileSet(int row, int column, Drawable drawable) {
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
				File f = container.showSave();
				game.save(f);
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
		try {
			game.rotate(row, column);
		} catch (RotationNotSupportedException e) {
			// Don't do anything
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
			game.save(f);
		}
	}

	/**
	 * Starts the loaded game
	 */
	private void startGame() {
		container.setGame(game.getBoardHeight(), game.getBoardWidth());
		game.start(this);
		container.setGameVisible(true);
	}

	/**
	 * Shows win message
	 */
	public void onWin() {
		container.showWinMessage();
	}
}
