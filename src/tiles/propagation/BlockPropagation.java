package tiles.propagation;

import game.Ray;

import java.awt.Color;
import java.util.Stack;

import tiles.Tile;

/**
 * Propagation component that blocks all rays. Remember that even though the ray
 * is stopped, it still leaves its mark in the direction it came from.
 */
public class BlockPropagation extends PropagationComponent {

	private static final long serialVersionUID = -4923341233311691910L;

	public BlockPropagation(Tile tile) {
		super(tile);
	}

	public void process(Ray ray, Stack<Ray> bifurcations) {
		setOrigin(ray);
		ray.stop();
	}

	public boolean hasRays() {
		return false;
	}
	
	public boolean hasRay(Color color) {
		return false;
	}

}
