package tiles;

import game.Ray;

import java.awt.Color;

/**
 * Target. Does not support rotation or movement
 */
public class Target extends ColoredTile {
	
	protected RotationComponent rotation = new NoRotation();
	
	public Target(Color color) {
		super(color);
	}
	
	public boolean isFixed() {
		return true;
	}
	
	public void react(Ray ray){
		
	}
	
}
