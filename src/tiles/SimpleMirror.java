package tiles;

import misc.Direction;
import game.Ray;
import game.TileDrawer;

/**
 * Simple Mirror. Supports rotation and movement
 */
public class SimpleMirror extends Tile {
	
	protected RotationComponent rotation = new FourWayRotation();

	public SimpleMirror(Direction direction) {
		rotation.setDirection(direction);
	}
	
	public void react(Ray ray){
		ray.reflect();
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}
}
