package tiles;

import misc.Direction;

public interface Rotatable {

	public abstract Direction getOrientation();

	public abstract void rotate();

}