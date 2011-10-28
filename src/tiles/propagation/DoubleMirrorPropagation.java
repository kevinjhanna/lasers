package tiles.propagation;

import tiles.Tile;
import game.Ray;
import misc.Direction;

public class DoubleMirrorPropagation extends MirrorPropagation {

	public DoubleMirrorPropagation(Tile tile) {
		super(tile);
	}

	public void process(Ray ray) {
		setOrigin(ray);

		Direction dTile = getDirection();
		Direction dRay = ray.getDirection();

		Direction m1 = mirrorDirection(dTile, dRay);
		if (m1 != null) {
			setRay(m1, ray);
			ray.move(m1);
		} else {
			Direction m2 = mirrorDirection(dTile.getOpposite(), dRay);
			if (m2 != null) {
				setRay(m2, ray);
				ray.move(m2);
			}			
		}
	}

}
