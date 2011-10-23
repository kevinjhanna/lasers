package frontend;

import java.awt.Image;

public interface View {

	public abstract void setCellImage(int row, int column, Image image);

	public abstract void updateScore(int score);

}