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

	/**
	 * Constructor de un tablero de dimensiones datas Las dimensiones no pueden
	 * ser menores a 5 ni mayores a 25, tanto de ancho como de alto
	 * 
	 * @param height
	 * @param width
	 */
	public Board(int height, int width) {

		if (width < 5 || height < 5 || width > 25 || height > 25) {
			throw new InvalidBoardSizeException();
		}

		content = new Tile[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				content[i][j] = new EmptyTile();
			}
		}
	}

	/**
	 * Retorna la celda ubicada en la posici—n dada
	 * 
	 * @param p
	 * @return Tile
	 */
	public Tile getTile(Position p) {
		return content[p.row][p.column];
	}

	/**
	 * Reemplaza una celda en la posici—n dada
	 * 
	 * @param p
	 * @param tile
	 */
	public void setTile(Position p, Tile tile) {
		content[p.row][p.column] = tile;
	}

	/**
	 * Mueve una celda desde una posici—n a la otra. La celda origen se completa
	 * con una nueva celda vac’a
	 * 
	 * @param src
	 * @param dst
	 * @throws SourceTileEmptyException
	 * @throws TargetTileNotEmptyException
	 */
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
