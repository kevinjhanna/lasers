package game;

import misc.Position;
import tiles.EmptyTile;
import tiles.Tile;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;
import exceptions.TileIsFixedException;

/**
 * Class that models a board in the game
 */
public class Board {

	private Tile[][] content;

	/**
	 * Creates a new board with the given dimensions
	 * 
	 * @param height
	 * 		The height of the new board
	 * @param width
	 * 		The width of the new board
	 */
	public Board(int height, int width) {

		content = new Tile[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				content[i][j] = new EmptyTile();
			}
		}
	}

	/**
	 * Returns the tile at the given position
	 * 
	 * @param p
	 * 		The position to look
	 * @return Tile
	 */
	public Tile getTile(Position p) {
		return content[p.row][p.column];
	}

	/**
	 * Replaces the tile at the given position by the tile given as parameter
	 * 
	 * @param p
	 * 		The position to replace
	 * @param tile
	 * 		The tile to put in position
	 */
	public void setTile(Position p, Tile tile) {
		content[p.row][p.column] = tile;
	}

	/**
	 * Moves a tile from a source position to a target position
	 * 
	 * @param source
	 * 		The source position
	 * @param target
	 * 		The target position
	 * @throws TileIsFixedException
	 * @throws SourceTileEmptyException
	 * @throws TargetTileNotEmptyException
	 */
	public void moveTile(Position source, Position target)
			throws TileIsFixedException, SourceTileEmptyException,
			TargetTileNotEmptyException {

		if (getTile(source).isFixed()) {
			throw new TileIsFixedException();
		}
		if (getTile(source).isEmpty()) {
			throw new SourceTileEmptyException();
		}
		if (!getTile(target).isEmpty()) {
			throw new TargetTileNotEmptyException();
		}

		Tile tile = getTile(source);
		setTile(source, new EmptyTile());
		setTile(target, tile);
	}

}
