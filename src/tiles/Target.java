package tiles;

import game.Ray;

import java.awt.Color;

public class Target extends ColoredTile {
	
	public Target(Color color) {
		this.color = color;
	}
	
	public boolean isFixed() {
		return true;
	}
	
	public void react(Ray ray){
		
	}
	
}
