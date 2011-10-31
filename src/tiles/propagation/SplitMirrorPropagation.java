package tiles.propagation;

import tiles.Tile;
import misc.Direction;
import game.Ray;

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
	public Ray process(Ray ray) {
		setOrigin(ray);

		Direction dTile = getDirection();
		Direction dRay = ray.getDirection();

		Direction m1 = mirrorDirection(dTile, dRay);
		if (m1 != null) {
			setRay(m1, ray);
			setRay(ray.getDirection(), ray);
			return ray.bifurcate(m1);
		} else {
			Direction m2 = mirrorDirection(dTile.getOpposite(), dRay);
			if (m2 != null) {
				setRay(m2, ray);
				setRay(ray.getDirection(), ray);
				return ray.bifurcate(m2);
			}
		}
		return null;
	}

}
