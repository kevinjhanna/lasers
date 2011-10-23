package frontend;

import game.Controller;
import game.Game;
import game.SourceTileEmptyException;
import game.TargetTileNotEmptyException;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import tiles.RotationNotSupportedException;
import tiles.Tile;

public class GameController implements Controller {

	private Game game;
	private Window window;
	private ImageFactory image = ImageFactory.getInstance();

	/**
	 * Create the frame.
	 */
	public GameController() {
		window = new Window(this);
		window.setVisible(true);
	}

	public void rotate(int row, int column) {
		try {
			game.rotate(row, column);
		} catch (RotationNotSupportedException e) {
			JOptionPane.showMessageDialog(window, "Can't rotate tile.",
					"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void move(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn) {

		try {
			game.move(sourceRow, sourceColumn, targetRow, targetColumn);
		} catch (SourceTileEmptyException e) {
			JOptionPane.showMessageDialog(window, "Source tile is empty.",
					"Warning", JOptionPane.WARNING_MESSAGE);
		} catch (TargetTileNotEmptyException e) {
			JOptionPane.showMessageDialog(window, "Target tile is not empty.",
					"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void saveGame() {
		if (game == null) {
			throw new NoGameException();
		}

		JFileChooser c = new JFileChooser();

		int ret = c.showSaveDialog(window);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File f = c.getSelectedFile();
			game.save(f);
		}
	}

	public void newGame() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("board"));

		int ret = fc.showOpenDialog(window);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();

			try {
				game = Game.fromBoardFile(f);
				startGame();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(window, e.toString(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void startGame() {
		window.setGame(game.getBoardHeight(), game.getBoardWidth());
		game.start(this);
		window.setGameVisible(true);
	}

	@Override
	public void loadGame() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExtensionFileFilter("save"));

		int ret = fc.showOpenDialog(window);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			try {
				game = Game.fromSaveFile(f);
				startGame();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(window, e.toString(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void closeGame() {
		if (game != null) {
			int ret = JOptionPane.showConfirmDialog(window,
					"Close without save?", "Close",
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (ret == JOptionPane.CANCEL_OPTION
					|| ret == JOptionPane.CLOSED_OPTION) {
				return;
			}
			if (ret == JOptionPane.NO_OPTION) {
				saveGame();
			}
			window.setGameVisible(false);
		}
	}

	@Override
	public void quit() {
		if (game != null) {
			int ret = JOptionPane.showConfirmDialog(window,
					"Quit without save?", "Quit",
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (ret == JOptionPane.NO_OPTION) {
				saveGame();
			}
			if (ret == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		System.exit(0);
	}

	@Override
	public void onTileMove(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn, Tile tile) {

		window.getGamePanel().setCellImage(sourceRow, sourceColumn, null);
		window.getGamePanel().setCellImage(targetRow, targetColumn,
				image.forTile(tile));
	}

	@Override
	public void onTileRotated(int row, int column, Tile tile) {
		window.getGamePanel().setCellImage(row, column, image.forTile(tile));
	}

	@Override
	public void onTileSet(int row, int column, Tile tile) {
		window.getGamePanel().setCellImage(row, column, image.forTile(tile));
	}

	@Override
	public void onScoreChange(int score) {
		window.getGamePanel().updateScore(score);
	}
}
