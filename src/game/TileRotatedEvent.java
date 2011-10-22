package game;
import tiles.Tile;

public class TileRotatedEvent {
	
	private final int row;
	private final int column;
	private final Tile tile;
	
	public TileRotatedEvent(int row, int column, Tile tile) {
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
