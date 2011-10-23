package frontend;

import game.Game;
import game.Observer;
import game.SourceTileEmptyException;
import game.TargetTileNotEmptyException;

import java.io.File;
import java.io.IOException;

import tiles.RotationNotSupportedException;
import tiles.Tile;

/**
 * Controlador principal del juego. Contiene un ViewContainer para mostrar el
 * juego en pantalla
 */
public class GameController implements Controller, Observer {

	private Game game;
	private ViewContainer container;

	/**
	 * Constructor del controlador
	 * 
	 * @param container
	 */
	public GameController(ViewContainer container) {
		this.container = container;
		container.setController(this);
		container.initialize();
		container.setVisible(true);
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
			container.showWarning("Can't rotate tile.");
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
	 * Crea un juego nuevo a partir de un archivo de tablero
	 */
	public void newGame() {
		File f = container.showLoad("board");
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
	 * Comienza el juego creado
	 */
	private void startGame() {
		container.setGame(game.getBoardHeight(), game.getBoardWidth());
		game.start(this);
		container.setGameVisible(true);
	}

	/**
	 * Carga un juego a partir de un archivo de juego guardado
	 */
	@Override
	public void loadGame() {
		File f = container.showLoad("save");
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
	 * Actualiza las celdas del tablero correspondientes cuando ocurre un
	 * movimiento de piezas
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 * @param tile
	 */
	@Override
	public void onTileMove(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn, Tile tile) {

		container.getView().setCellImage(sourceRow, sourceColumn, null);
		container.getView().setCellImage(targetRow, targetColumn, tile);
	}

	/**
	 * Actualiza la celda del tablero correspondiente cuando ocurre una rotaci—n
	 * de pieza
	 * 
	 * @param row
	 * @param column
	 * @param tile
	 */
	@Override
	public void onTileRotated(int row, int column, Tile tile) {
		container.getView().setCellImage(row, column, tile);
	}

	/**
	 * Actualiza la celda del tablero correspondiente cuando se a–ade una pieza
	 * 
	 * @param row
	 * @param column
	 * @param tile
	 */
	@Override
	public void onTileSet(int row, int column, Tile tile) {
		container.getView().setCellImage(row, column, tile);
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
}
