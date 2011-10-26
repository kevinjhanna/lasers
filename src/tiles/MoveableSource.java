	package tiles;


import java.awt.Color;

import misc.Direction;

public class MoveableSource extends Source {
	
	protected RotationComponent rotation = new FourWayRotation();
	
	public MoveableSource(Color color, Direction direction) {
		super(color, direction);
		rotation.setDirection(direction);
	}
	
	public boolean isFixed() {
		return false;
	}


	
}
