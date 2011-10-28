package frontend;

/**
 * Represents the controller in the MVC implementation. Interacts with the model
 * (game logic) and the view
 */
public interface Controller {

	/**
	 * Creates a new game
	 */
	void newGame();

	/**
	 * Loads a game
	 */
	void loadGame();

	/**
	 * Saves the current game
	 */
	void saveGame();

	/**
	 * Closes the current game
	 */
	void closeGame();

	/**
	 * Quits the program
	 */
	void quit();

	/**
	 * Rotates the tile at the location specified
	 * 
	 * @param row
	 * @param column
	 */
	void rotate(int row, int column);

	/**
	 * Moves the tile to the location specified
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 */
	void move(int sourceRow, int sourceColumn, int targetRow, int targetColumn);

}
