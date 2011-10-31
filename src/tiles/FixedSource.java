package tiles;

import java.awt.Color;

import misc.Direction;

/**
 * A <tt>Source</tt> tile that does not support rotation or movement.
 */
public class FixedSource extends Source {

	private static final long serialVersionUID = -7755886326140761567L;

	/**
	 * Creates a new source tile.
	 * 
	 * @param color
	 * @param direction
	 */
	public FixedSource(Color color, Direction direction) {
		super(color, direction);
	}

	/**
	 * Returns true because this tile is fixed in its position.
	 */
	public boolean isFixed() {
		return true;
	}

}
