package tiles;

public class EmptyTile extends Tile {

	public static final String name = "EmptyTile";
	
	public boolean isEmpty() {
		return true;
	}

	@Override
	public String getName() {
		return name;
	}
	
}