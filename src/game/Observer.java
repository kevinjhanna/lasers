package game;

import tiles.Tile;

public interface Observer {

	void onTileMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn, Tile tile);

	void onTileRotated(int row, int column, Tile tile);

	void onTileSet(int row, int column, Tile value);

	void onScoreChange(int score);
	
}
