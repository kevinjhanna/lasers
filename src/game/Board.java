package game;

import java.io.Serializable;

import misc.Position;
import tiles.EmptyTile;
import tiles.Tile;
import exceptions.InvalidBoardSizeException;
import exceptions.PositionOutOfBounds;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;
import exceptions.TileIsFixedException;

/**
 * A 'generic' game board
 */
public class Board implements Serializable {

	private static final long serialVersionUID = -343121504836873833L;

	private Tile[][] content;
	private int height;
	private int width;

	/**
	 * Creates a new board with the given dimensions
	 * 
	 * @param height
	 *            The height of the new board
	 * @param width
	 *            The width of the new board
	 * @throws InvalidBoardSizeException
	 */
	public Board(int height, int width) {

		this.width = width;
		this.height = height;
		content = new Tile[height][width];

		fillEmpty();
	}



	/**
	 * Returns the tile at the given position
	 * 
	 * @param p
	 * @return Tile
	 */
	public Tile getTile(Position p) {
		if (!validPosition(p)) {
			throw new PositionOutOfBounds();
		}
		return content[p.row][p.column];
	}

	/**
	 * Replaces the tile at the given position by the tile given as parameter
	 * 
	 * @param p
	 * @param tile
	 */
	public void setTile(Position p, Tile tile) {
		if (!validPosition(p)) {
			throw new PositionOutOfBounds();
		}
		content[p.row][p.column] = tile;
	}

	/**
	 * Moves a tile from a source position to a target position
	 * 
	 * @param source
	 *            The source position
	 * @param target
	 *            The target position
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

	/**
	 * Checks that the position given falls inside the board boundaries
	 * 
	 * @param p
	 *            the position to be checked
	 * @return boolean
	 */
	public boolean validPosition(Position p) {
		return (p.row >= 0 && p.row < height && p.column >= 0 && p.column < width);
	}

	/**
	 * Returns the width of the board
	 * 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the board
	 * 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Resets the board to blank state
	 */
	public void reset() {
		fillEmpty();
	}

	/**
	 * Fills the board with empty cells
	 */
	private void fillEmpty() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				content[i][j] = new EmptyTile();
			}
		}
	}

}
