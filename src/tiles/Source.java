package tiles;

import java.awt.Color;

import misc.Direction;
import game.TileDrawer;

/**
 * Source. Does not support rotation or movement
 * @author federicobond
 *
 */
public class Source extends ColoredTile {	
	
private Direction direction;
	
	public Source(Color color, Direction direction) {
		super(color);
		this.direction = direction;
	}
	
	public boolean isFixed() {
		return true;
	}

	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), direction);
	}
}
