package tiles;

import game.Ray;
import game.TileDrawer;
import misc.Direction;

/**
 * Simple Mirror. Supports rotation and movement
 */
public class SimpleMirror extends Tile {
	
	public SimpleMirror(Direction direction) {
		setDirection(direction);
	}
	
	public void hit(Ray ray) {
		switch (getDirection()) {
		case EAST:
			if (ray.getDirection() == Direction.EAST) {
				ray.move(Direction.SOUTH);
			} else if (ray.getDirection() == Direction.SOUTH) {
				ray.move(Direction.WEST);
			}
			break;
		case SOUTH:
			if (ray.getDirection() == Direction.EAST) {
				ray.move(Direction.NORTH);
			} else if (ray.getDirection() == Direction.SOUTH) {
				ray.move(Direction.WEST);
			}
			break;
		case WEST:
			if (ray.getDirection() == Direction.WEST) {
				ray.move(Direction.NORTH);
			} else if (ray.getDirection() == Direction.SOUTH) {
				ray.move(Direction.EAST);
			}
			break;
		case NORTH:
			if (ray.getDirection() == Direction.WEST) {
				ray.move(Direction.SOUTH);
			} else if (ray.getDirection() == Direction.NORTH) {
				ray.move(Direction.EAST);
			}
			break;
		}
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), getDirection());
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new FourWayDirection(true);
	}
}
