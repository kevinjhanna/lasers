package tiles.propagation;

import tiles.Tile;
import game.Ray;

public class BlockPropagation extends PropagationComponent {

	public BlockPropagation(Tile tile) {
		super(tile);
	}
	
	public void process(Ray ray) {
		setOrigin(ray);
	}
	
	public boolean hasRays() {
		return false;
	}
	
}
