package game;

import tiles.Drawable;

/**
 * Interface with callbacks from the game.
 */
public interface Observer {

	/**
	 * Callback that fires when a tile has been updated.
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */
	void onTileUpdate(int row, int column, Drawable drawable);

	/**
	 * Callback that fires when the score has changed.
	 * 
	 * @param newScore
	 */
	void onScoreChange(int newScore);

	/**
	 * Callback that fires when the game is won.
	 */
	void onWin();
}
