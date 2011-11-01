package tiles.propagation;

import java.util.Stack;

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

	public void process(Ray ray, Stack<Ray> bifurcations) {
		setOrigin(ray);
		setBeam(ray.getDirection(), ray);
	}

}
