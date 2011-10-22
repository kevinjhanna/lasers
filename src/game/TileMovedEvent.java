package game;
import tiles.Tile;

public class TileMovedEvent {

	private final int sourceRow;
	private final int sourceColumn;
	private final int targetRow;
	private final int targetColumn;
	private final Tile tile;
	
	public TileMovedEvent(int sourceRow, int sourceColumn, int destRow, int destColumn, Tile tile) {
		this.sourceRow = sourceRow;
		this.sourceColumn = sourceColumn;
		this.targetRow = destRow;
		this.targetColumn = destColumn;
		this.tile = tile;
	}

	public int getSourceRow() {
		return sourceRow;
	}

	public int getSourceColumn() {
		return sourceColumn;
	}

	public int getTargetRow() {
		return targetRow;
	}

	public int getTargetColumn() {
		return targetColumn;
	}

	public Tile getTile() {
		return tile;
	}
}
