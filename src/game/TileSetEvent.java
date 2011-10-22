package game;
import tiles.Tile;


public class TileSetEvent {

	private final int row;
	private final int column;
	private final Tile tile;
	
	public TileSetEvent(int row, int column, Tile tile) {
		this.row = row;
		this.column = column;
		this.tile = tile;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Tile getTile() {
		return tile;
	}
}
