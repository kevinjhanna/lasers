package tiles;

import java.awt.Color;

import misc.Direction;

/**
 * A <tt>MoveableSource</tt> tile that supports movement but not rotation.
 */
public class MoveableSource extends Source {

	private static final long serialVersionUID = -5430284811313280074L;

	public MoveableSource(Color color, Direction direction) {
		super(color, direction);
	}

	public boolean isFixed() {
		return false;
	}

}
