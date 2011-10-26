package tiles;

import misc.Direction;
import game.Ray;
import game.TileDrawer;

public class SimpleMirror extends Tile {
	
	private Direction direction;

	public SimpleMirror(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void rotate() {
		direction = direction.turn();
	}
	
	public void react(Ray ray){
		ray.reflect();
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), direction);
	}
}
