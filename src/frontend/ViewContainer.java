package frontend;

import java.io.File;

/**
 * A container for the main game view. The class that implements this interface
 * is supposed to handle things that fall outside of the responsibility of the
 * <tt>View</>
 * 
 * @see View
 */
public interface ViewContainer {

	/**
	 * Configures the view to work with a board of the given dimensions.
	 * 
	 * @param boardHeight
	 * @param boardWidth
	 */
	public abstract void setGame(int boardHeight, int boardWidth);

	/**
	 * Returns a reference to the game view
	 * 
	 * @return View
	 */
	public abstract View getView();

	/**
	 * Sets the visibility of the game. If the game is not visible, the view
	 * should present a friendly interface for creating or loading games.
	 * 
	 * @param b
	 */
	public abstract void setGameVisible(boolean b);

	/**
	 * Shows a warning message
	 * 
	 * @param message
	 */
	public abstract void showWarning(String message);

	/**
	 * Shows an error message
	 * 
	 * @param message
	 */
	public abstract void showError(String message);

	/**
	 * Asks the user to confirm a message and returns the result
	 * 
	 * @param message
	 * @return ConfirmOption
	 */
	public abstract ConfirmOption showConfirm(String message);

	/**
	 * Shows a save interface and returns the selected location as a
	 * <tt>File</tt>.
	 * 
	 * @return File
	 */
	public abstract File showSave();

	/**
	 * Shows a load interface and returns the <tt>File</tt> to load.
	 * 
	 * @return File
	 */
	public abstract File showLoad();

	/**
	 * Shows a new game interface and returns the <tt>File</tt> to load as a new
	 * game.
	 * 
	 * @return File
	 */
	public abstract File showNew();

	/**
	 * Informs the user that he/she has won the game
	 */
	public abstract void showWin();

}