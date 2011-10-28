package tiles.propagation;

import tiles.Tile;
import misc.Direction;
import game.Ray;

public class SplitMirrorPropagation extends MirrorPropagation {

	public SplitMirrorPropagation(Tile tile) {
		super(tile);
	}

	@Override
	public void process(Ray ray) {
		setOrigin(ray);

		Direction dTile = getDirection();
		Direction dRay = ray.getDirection();

		Direction m1 = mirrorDirection(dTile, dRay);
		if (m1 != null) {
			setRay(m1, ray);
			setRay(ray.getDirection(), ray);
			ray.bifurcate(ray.getDirection(), m1);
		} else {
			Direction m2 = mirrorDirection(dTile.getOpposite(), dRay);
			if (m2 != null) {
				setRay(m2, ray);
				setRay(ray.getDirection(), ray);
				ray.bifurcate(ray.getDirection(), m2);
			}
		}

	}

}
