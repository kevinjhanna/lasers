package tiles.propagation;

import tiles.Tile;
import game.Ray;

/**
 * Propagation component that blocks all rays. Remember that even though the ray
 * is stopped, it still leaves its mark in the direction it came from
 * 
 */
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
