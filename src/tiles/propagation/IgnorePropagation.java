package tiles.propagation;

import tiles.Tile;
import game.Ray;

/**
 * Propagation component that lets ray pass without bothering them
 */
public class IgnorePropagation extends PropagationComponent {

	public IgnorePropagation(Tile tile) {
		super(tile);
	}

	public void process(Ray ray) {
		setOrigin(ray);
		setRay(ray.getDirection(), ray);
		ray.move();
	}

}
