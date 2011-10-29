package tiles.propagation;

import tiles.Tile;
import game.Ray;

/**
 * Propagation component that lets ray pass without bothering them.
 */
public class IgnorePropagation extends PropagationComponent {

	private static final long serialVersionUID = -4550326291550889273L;

	public IgnorePropagation(Tile tile) {
		super(tile);
	}

	public Ray process(Ray ray) {
		setOrigin(ray);
		setRay(ray.getDirection(), ray);
		return null;
	}

}
