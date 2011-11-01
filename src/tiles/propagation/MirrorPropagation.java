package tiles.propagation;

import game.Ray;

import java.util.Stack;

import misc.Direction;
import tiles.Tile;

/**
 * Propagation component that changes the direction of the ray in a mirrored
 * way.
 */
public class MirrorPropagation extends PropagationComponent {

	private static final long serialVersionUID = -3757269441943082244L;

	public MirrorPropagation(Tile tile) {
		super(tile);
	}

	public void process(Ray ray, Stack<Ray> bifurcations) {
		setOrigin(ray);

		Direction dTile = getDirection();
		Direction dRay = ray.getDirection();

		Direction mirror = mirrorDirection(dTile, dRay);
		if (mirror != null) {
			setBeam(mirror, ray);
			ray.setDirection(mirror);
		} else {
			ray.stop();
		}
	}

	protected final Direction mirrorDirection(Direction tile, Direction ray) {
		if (ray == tile) {
			return tile.turn();
		}
		if (ray.turn() == tile) {
			return tile.getOpposite();
		}
		return null;
	}

}
