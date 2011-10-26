package tiles;

import java.awt.Color;

import misc.Direction;
import game.Ray;
import game.TileDrawer;

/**
 * Filter. Supports rotation and movement
 * @author federicobond
 *
 */
public class Filter extends ColoredTile {
	
	protected RotationComponent rotation = new TwoWayRotation();
	
	public Filter(Color color, Direction direction) {
		super(color);
		rotation.setDirection(direction);
	}

	public void react(Ray ray){
		ray.changeColor();//dummy
		ray.moveStraight();
	
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}
}
