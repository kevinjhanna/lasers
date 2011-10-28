package tiles.propagation;

import tiles.Tile;
import game.Ray;
import misc.Direction;

public class MirrorPropagation extends PropagationComponent {

	public MirrorPropagation(Tile tile) {
		super(tile);
	}
	
	public void process(Ray ray) {
		setOrigin(ray);
		
		Direction dTile = getDirection();
		Direction dRay = ray.getDirection();
		
		Direction mirror = mirrorDirection(dTile, dRay);
		if (mirror != null) {
			setRay(mirror, ray);
			ray.move(mirror);
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
