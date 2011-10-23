package game;

import tiles.Tile;

public interface Controller {

	void newGame();
	
	void loadGame();
	
	void saveGame();
	
	void closeGame();

	void quit();

	void rotate(int row, int column);

	void move(int sourceRow, int sourceColumn, int targetRow, int targetColumn);

	void onTileMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn, Tile tile);

	void onTileRotated(int row, int column, Tile tile);

	void onTileSet(int row, int column, Tile value);

	void onScoreChange(int score);

}
