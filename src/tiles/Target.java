package tiles;

import java.awt.Color;

public class Target extends ColoredTile {

	public static final String name = "Target";

	@Override
	public String getName() {
		return name;
	}
	
	public Target(Color color) {
		this.color = color;
	}
}
