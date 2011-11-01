package tests;

import game.Observer;
import tiles.Cell;

/**
 * Implementation of the observer interface that does not act on any callback.
 * Useful for testing.
 */
public class NullObserver implements Observer {

	@Override
	public void onCellUpdate(int row, int column, Cell cell) {
	}

	@Override
	public void onScoreChange(int newScore) {
	}

	@Override
	public void onWin() {
	}

}
