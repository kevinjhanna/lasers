package tiles;

import game.Ray;
import game.TileDrawer;
import misc.Direction;

/**
 * Split Mirror. Supports rotation and movement
 */
public class SplitMirror extends Tile {
	
	public SplitMirror(Direction direction) {
		setRotationComponent(new TwoWayRotation());
		setDirection(direction);
	}
	
	public void react(Ray ray){
		System.out.println("collaps with split");

		ray.bifurcate();
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}
}
