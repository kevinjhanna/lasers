package game;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import misc.Direction;
import misc.Position;
import tiles.DoubleMirror;
import tiles.Filter;
import tiles.Rotatable;
import tiles.RotationNotSupportedException;
import tiles.SimpleMirror;
import tiles.Source;
import tiles.Target;
import tiles.Tile;
import tiles.Wall;

public class Game extends Observable {

	private Integer boardHeight;
	private Integer boardWidth;
	private Integer score;
	private Board board;

	public static Game fromBoardFile(File f, Observer o) throws IOException {
		System.out.println("Archivo cargado de " + f.getName());

		Map<Position, Tile> tiles = new HashMap<Position, Tile>();
		tiles.put(new Position(3, 4), new Wall());
		tiles.put(new Position(3, 5), new Wall());
		tiles.put(new Position(2, 4), new Wall());
		tiles.put(new Position(1, 7), new Wall());
		tiles.put(new Position(8, 8), new Filter(new Color(0, 255, 0),
				Direction.NORTH));
		tiles.put(new Position(8, 6), new Source(new Color(255, 0, 0),
				Direction.SOUTH));
		tiles.put(new Position(7, 7), new SimpleMirror(Direction.EAST));
		tiles.put(new Position(6, 6), new DoubleMirror(Direction.WEST));
		tiles.put(new Position(5, 5), new Target(new Color(233, 200, 150)));
		return new Game(o, 10, 10, tiles);
	}

	public static Game fromSaveFile(File f, Observer o) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getBoardWidth() {
		return boardWidth;
	}

	public Integer getBoardHeight() {
		return boardHeight;
	}

	public Tile getTile(int row, int column) {
		return board.getTile(new Position(row, column));
	}

	public void move(int sourceRow, int sourceColumn, int destRow,
			int destColumn) throws SourceTileEmptyException,
			TargetTileNotEmptyException {

		board.moveTile(new Position(sourceRow, sourceColumn), new Position(
				destRow, destColumn));
		setChanged();
		notifyObservers(new TileMovedEvent(sourceRow, sourceColumn, destRow,
				destColumn, getTile(destRow, destColumn)));
	}

	public void rotate(int row, int column) throws RotationNotSupportedException {
		Tile tile = board.getTile(new Position(row, column));
		if (tile instanceof Rotatable) {
			((Rotatable) tile).rotate();
		} else {
			throw new RotationNotSupportedException();
		}
		setChanged();
		notifyObservers(new TileRotatedEvent(row, column, getTile(row, column)));
	}

	public void save(File f) {
		System.out.println("Archivo guardado en " + f.getName());
	}

	private void updateScore() {
		score = 42;
		setChanged();
		notifyObservers(new ScoreChangedEvent(score));
	}

	private Game(Observer o, int boardWidth, int boardHeight,
			Map<Position, Tile> tiles) {

		addObserver(o);

		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		board = new Board(boardWidth, boardHeight);
		populateBoard(tiles);
		updateScore();
	}

	private void populateBoard(Map<Position, Tile> tiles) {
		for (Map.Entry<Position, Tile> e : tiles.entrySet()) {
			board.setTile(e.getKey(), e.getValue());
			setChanged();
			notifyObservers(new TileSetEvent(e.getKey().row, e.getKey().column,
					e.getValue()));
		}
	}

}
