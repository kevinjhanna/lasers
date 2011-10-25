package tiles;

import java.awt.Color;

/**
 * Target. Does not support rotation or movement
 */
public class Target extends ColoredTile {
	
	public Target(Color color) {
		super(color);
	}
	
	public boolean isFixed() {
		return true;
	}
}
