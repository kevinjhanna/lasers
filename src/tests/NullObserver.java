package tests;

import tiles.Drawable;
import game.Observer;

/**
 * Implementation of the observer interface that does not act on any callback.
 * Useful for testing.
 */
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
