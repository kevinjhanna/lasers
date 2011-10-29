package tests;

import tiles.Drawable;
import game.Observer;

public class NullObserver implements Observer {

	@Override
	public void onTileUpdate(int row, int column, Drawable drawable) {
	}

	@Override
	public void onScoreChange(int newScore) {
	}

	@Override
	public void onWin() {
	}

}
