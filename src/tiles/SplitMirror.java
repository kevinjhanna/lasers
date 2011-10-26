package tiles;

import misc.Direction;
import game.Ray;
import game.TileDrawer;

public class SplitMirror extends Tile {

	public Direction direction;

	public SplitMirror(Direction direction) {
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
		System.out.println("collaps with split");

		ray.bifurcate();
	}
	
	@Override
	public <T> T draw(TileDrawer<T> drawer) {
		return drawer.withDirection(super.draw(drawer), direction);
	}
}
