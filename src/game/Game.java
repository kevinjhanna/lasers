package game;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

public class Game {

	private Controller controller;
	private Integer boardHeight;
	private Integer boardWidth;
	private Integer score;
	private Board board;
	private Map<Position, Tile> initialTiles;

	public static Game fromBoardFile(File f) throws IOException {
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
		return new Game(10, 15, tiles);
	}

	public static Game fromSaveFile(File f) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public void start(Controller controller) {
		this.controller = controller;
		populateBoard();
		updateScore();
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

	public void move(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn) throws SourceTileEmptyException,
			TargetTileNotEmptyException {

		Position source = new Position(sourceRow, sourceColumn);
		Position target = new Position(targetRow, targetColumn);
		board.moveTile(source, target);

		controller.onTileMove(sourceRow, sourceColumn, targetRow, targetColumn,
				getTile(targetRow, targetColumn));
	}

	public void rotate(int row, int column)
			throws RotationNotSupportedException {
		Tile tile = board.getTile(new Position(row, column));
		if (tile instanceof Rotatable) {
			((Rotatable) tile).rotate();
		} else {
			throw new RotationNotSupportedException();
		}
		controller.onTileRotated(row, column, getTile(row, column));
	}

	public void save(File f) {
		System.out.println("Archivo guardado en " + f.getName());
	}

	private void updateScore() {
		score = 42;
		controller.onScoreChange(score);
	}

	private Game(int boardHeight, int boardWidth,
			Map<Position, Tile> initialTiles) {

		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.initialTiles = initialTiles;

		board = new Board(boardHeight, boardWidth);
	}

	private void populateBoard() {
		for (Map.Entry<Position, Tile> e : initialTiles.entrySet()) {
			board.setTile(e.getKey(), e.getValue());

			controller.onTileSet(e.getKey().row, e.getKey().column,
					e.getValue());
		}
	}

}
