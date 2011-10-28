package frontend;

import tiles.Drawable;

/**
 * Interface that defines the actions that can be performed in a <tt>View</tt>.
 */
public interface View {

	/**
	 * Updated the cell located at the given row and column with the drawing of
	 * the parameter
	 * 
	 * @param row
	 *            The row of the cell
	 * @param column
	 *            The column of the cell
	 * @param drawable
	 *            The tile to draw in the cell
	 */
	public abstract void updateCell(int row, int column, Drawable drawable);

	public abstract void clearCell(int row, int column);

	public abstract void updateScore(int score);

}