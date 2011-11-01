package frontend;

import game.Cell;

/**
 * Interface that defines the actions that can be performed in a <tt>View</tt>.
 */
public interface View {

	/**
	 * Updates the cell located at the specified row and column with the drawing
	 * of the parameter.
	 * 
	 * @param row
	 * @param column
	 * @param cell
	 */
	public abstract void updateCell(int row, int column, Cell cell);

	/**
	 * Clears the cell located at the specified row and column.
	 * 
	 * @param row
	 * @param column
	 */
	public abstract void clearCell(int row, int column);

	/**
	 * Updates the game score.
	 * 
	 * @param score
	 */
	public abstract void updateScore(int score);

}