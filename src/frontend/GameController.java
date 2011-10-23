package frontend;

import game.Game;
import game.Observer;
import game.SourceTileEmptyException;
import game.TargetTileNotEmptyException;

import java.io.File;
import java.io.IOException;

import tiles.RotationNotSupportedException;
import tiles.Tile;

public class GameController implements Controller, Observer {

	private Game game;
	private ViewContainer container;
	private ImageFactory image = ImageFactory.getInstance();

	/**
	 * Create the frame.
	 */
	public GameController(ViewContainer container) {
		this.container = container;
		container.setController(this);
		container.initialize();
		container.setVisible(true);
	}

	public void rotate(int row, int column) {
		try {
			game.rotate(row, column);
		} catch (RotationNotSupportedException e) {
			container.showWarning("Can't rotate tile.");
		}
	}

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

	public void saveGame() {
		if (game == null) {
			throw new NoGameException();
		}

		File f = container.showSave();
		if (f != null) { 
			game.save(f);
		}
	}

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

	private void startGame() {
		container.setGame(game.getBoardHeight(), game.getBoardWidth());
		game.start(this);
		container.setGameVisible(true);
	}

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

	@Override
	public void onTileMove(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn, Tile tile) {

		container.getView().setCellImage(sourceRow, sourceColumn, null);
		container.getView().setCellImage(targetRow, targetColumn,
				image.forTile(tile));
	}

	@Override
	public void onTileRotated(int row, int column, Tile tile) {
		container.getView().setCellImage(row, column, image.forTile(tile));
	}

	@Override
	public void onTileSet(int row, int column, Tile tile) {
		container.getView().setCellImage(row, column, image.forTile(tile));
	}

	@Override
	public void onScoreChange(int score) {
		container.getView().updateScore(score);
	}
}
