package tiles;

import java.awt.Color;

/**
 * Target. Does not support rotation or movement
 */
public class Target extends ColoredTile {
	
	protected DirectionComponent rotation = new NoDirection();
	
	public Target(Color color) {
		super(color);
	}
	
	public boolean isFixed() {
		return true;
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new NoDirection();
	}

	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new IgnorePropagation(tile);
	}
	
}
