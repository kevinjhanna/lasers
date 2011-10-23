package frontend;

import tiles.Tile;

public interface View {

	public abstract void setCellImage(int row, int column, Tile tile);

	public abstract void updateScore(int score);

}