package tiles;

import game.Beam;
import game.Drawable;
import misc.Direction;

/**
 * Interface that represents a cell.
 * 
 * @see Tile
 */
public interface Cell extends Drawable {

	/**
	 * Returns the beam at the specified direction if exists or else null.
	 * 
	 * @param direction
	 * @return Beam
	 */
	public Beam getBeam(Direction direction);

}
