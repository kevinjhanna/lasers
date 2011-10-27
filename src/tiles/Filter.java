package tiles;

import java.awt.Color;

import misc.Direction;
import game.Ray;
import game.TileDrawer;

/**
 * Filter. Supports rotation and movement
 * 
 * @author federicobond
 * 
 */
public class Filter extends ColoredTile {

	public Filter(Color color, Direction direction) {
		super(color);
		setDirection(direction);
	}

	public void react(Ray ray) {

	}

	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}

	@Override
	protected RotationComponent getRotationComponent() {
		return new TwoWayRotation(true);
	}
}
