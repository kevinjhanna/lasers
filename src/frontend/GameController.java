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
 * Controlador principal del juego. Contiene un ViewContainer para mostrar el
 * juego en pantalla
 */
public class GameController implements Controller, Observer {

	private Game game;
	private ViewContainer container = new Window();

	/**
	 * Constructor del controlador
	 * 
	 * @param container
	 */
	public GameController() {
		container.setController(this);
		container.initialize();
		container.setVisible(true);
	}

	/**
	 * Cierra el juego
	 */
	@Override
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
	 * Carga un juego a partir de un archivo de juego guardado
	 */
	@Override
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
	 * Mueve una pieza del tablero de juego
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
			container.showWarning("Source tile is empty.");
		} catch (TargetTileNotEmptyException e) {
			container.showWarning("Target tile is not empty.");
		} catch (TileIsFixedException e) {
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
	 * Actualiza el puntaje mostrado cuando ocurre un cambio de puntaje
	 * 
	 * @param newScore
	 */
	@Override
	public void onScoreChange(int newScore) {
		container.getView().updateScore(newScore);
	}

	/**
	 * Actualiza las celdas del tablero correspondientes cuando ocurre un
	 * movimiento de piezas
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 * @param drawable
	 */
	@Override
	public void onTileMove(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn, Drawable drawable) {

		container.getView().setCell(sourceRow, sourceColumn, null);
		container.getView().setCell(targetRow, targetColumn, drawable);
	}

	/**
	 * Actualiza la celda del tablero correspondiente cuando ocurre una rotaci—n
	 * de pieza
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */
	@Override
	public void onTileRotated(int row, int column, Drawable drawable) {
		container.getView().setCell(row, column, drawable);
	}

	/**
	 * Actualiza la celda del tablero correspondiente cuando se a–ade una pieza
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */
	@Override
	public void onTileSet(int row, int column, Drawable drawable) {
		container.getView().setCell(row, column, drawable);
	}

	/**
	 * Sale del programa
	 */
	@Override
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
	 * Rota una pieza del tablero de juego
	 * 
	 * @param row
	 * @param column
	 */
	public void rotate(int row, int column) {
		try {
			game.rotate(row, column);
		} catch (RotationNotSupportedException e) {
		}
	}

	/**
	 * Guarda el juego en curso
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
	 * Comienza el juego creado
	 */
	private void startGame() {
		container.setGame(game.getBoardHeight(), game.getBoardWidth());
		game.start(this);
		container.setGameVisible(true);
	}
}
