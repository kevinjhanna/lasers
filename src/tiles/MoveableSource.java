package tiles;

import java.awt.Color;

import misc.Direction;

public class MoveableSource extends Source {
	
	public MoveableSource(Color color, Direction direction) {
		super(color, direction);
	}
	
	public boolean isFixed() {
		return false;
	}


	
}
