package game;

import tiles.Drawable;

/**
 * Interface with callbacks from the game
 */
public interface Observer {

	/**
	 * Callback that fires when a tile has been moved
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 * @param drawable
	 */
	void onTileMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn, Drawable drawable);

	/**
	 * Callback that fires when a tile has been rotated
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */
	void onTileRotated(int row, int column, Drawable drawable);

	/**
	 * Callback that fires when a tile has been set
	 * 
	 * @param row
	 * @param column
	 * @param drawable
	 */
	void onTileSet(int row, int column, Drawable drawable);

	/**
	 * Callback that fires when the score has changed
	 * 
	 * @param newScore
	 */
	void onScoreChange(int newScore);
	
	/**
	 * Callaback that fires when the game is won
	 */
	void onWin();
}
