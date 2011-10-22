package game;

import misc.Position;
import tiles.EmptyTile;
import tiles.Tile;

/**
 * Clase que representa un tablero del juego
 * 
 */
public class Board {

	private Tile[][] content;

	public Board(int width, int height) {
		content = new Tile[width][height];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				content[i][j] = new EmptyTile();
			}
		}
	}

	public Tile getTile(Position p) {
		return content[p.row][p.column];
	}

	public void setTile(Position p, Tile tile) {
		content[p.row][p.column] = tile;
	}

	public void moveTile(Position src, Position dst)
			throws SourceTileEmptyException, TargetTileNotEmptyException {

		if (getTile(src).isEmpty()) {
			throw new SourceTileEmptyException();
		}
		if (!getTile(dst).isEmpty()) {
			throw new TargetTileNotEmptyException();
		}

		Tile tile = getTile(src);
		setTile(src, new EmptyTile());
		setTile(dst, tile);
	}

}
