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

	public Beam getBeam(Direction direction);

}
