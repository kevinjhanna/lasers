package misc;
import java.awt.Color;


public class Beam {

	private final Color c;
	private final Direction d;
	
	public Beam(Color c, Direction d) {
		this.c = c;
		this.d = d;
	}
	
	public Color getColor() {
		return c;
	}
	public Direction getDirection() {
		return d;
	}
}