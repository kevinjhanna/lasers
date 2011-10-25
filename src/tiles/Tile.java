package tiles;

import java.util.*;

import exceptions.RotationNotSupportedException;
import misc.Direction;
import game.Ray;
import game.TileDrawer;

public abstract class Tile implements Drawable {
	private Ray[] rays = new Ray[4];
	
	
	/* each react depends on the tile implementation*/
	public abstract void react(Ray ray);
	
	
	public boolean isEmpty() {
		return false;
	}
	
	public boolean isFixed() {
		return false;
	} 

	public Direction getDirection() {
		return Direction.NORTH;
	}
	
	public void rotate() throws RotationNotSupportedException {
		throw new RotationNotSupportedException();
	}
	
	public <T> T draw(TileDrawer<T> tileDrawer) {
		return tileDrawer.draw(this.getClass().getName());
	}
}
