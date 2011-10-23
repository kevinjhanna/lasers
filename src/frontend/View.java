package frontend;

import tiles.Drawable;

public interface View {

	public abstract void setCellImage(int row, int column, Drawable drawable);

	public abstract void updateScore(int score);

}