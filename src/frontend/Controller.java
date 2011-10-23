package frontend;

public interface Controller {

	void newGame();

	void loadGame();

	void saveGame();

	void closeGame();

	void quit();

	void rotate(int row, int column);

	void move(int sourceRow, int sourceColumn, int targetRow, int targetColumn);

}
