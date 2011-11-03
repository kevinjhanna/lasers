package exceptions;

/**
 * Exception thrown when trying to move a tile to a position that is already
 * occupied by a non-empty tile.
 * 
 * @author federicobond
 * 
 */
public class TargetTileNotEmptyException extends RuntimeException {

	private static final long serialVersionUID = -8063237555153282580L;

}
