package game;

import tiles.Cell;

/**
 * Interface with callbacks from the game.
 */
public interface Observer {

	/**
	 * Callback that fires when a cell has been updated.
	 * 
	 * @param row
	 * @param column
	 * @param cell
	 */
	void onCellUpdate(int row, int column, Cell cell);

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
