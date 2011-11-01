package tiles.propagation;

import game.Ray;

import java.util.Stack;

import misc.Direction;
import tiles.Tile;

/**
 * Propagation component that lets rays continue in the direction they came from
 * but also reflects them like a mirror.
 */
public class SplitMirrorPropagation extends MirrorPropagation {

	private static final long serialVersionUID = -3524244017666461820L;

	public SplitMirrorPropagation(Tile tile) {
		super(tile);
	}

	@Override
	public void process(Ray ray, Stack<Ray> bifurcations) {
		setOrigin(ray);

		Direction dTile = getDirection();
		Direction dRay = ray.getDirection();

		Direction mirror = mirrorDirection(dTile, dRay);
		if (mirror == null) {
			mirror = mirrorDirection(dTile.getOpposite(), dRay);
		}
		setBeam(mirror, ray);
		setBeam(ray.getDirection(), ray);
		bifurcations.push(ray.bifurcate(mirror));
	}

}
