package tiles.propagation;

import tiles.Tile;
import game.Ray;
import misc.Direction;

/**
 * Propagation component that changes the direction of the ray in a mirrored
 * way.
 */
public class MirrorPropagation extends PropagationComponent {

	public MirrorPropagation(Tile tile) {
		super(tile);
	}

	public Ray process(Ray ray) {
		setOrigin(ray);

		Direction dTile = getDirection();
		Direction dRay = ray.getDirection();

		Direction mirror = mirrorDirection(dTile, dRay);
		if (mirror != null) {
			setRay(mirror, ray);
			ray.setDirection(mirror);
		} else {
			ray.stop();
		}
		return null;
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
