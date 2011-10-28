package tiles;

import java.awt.Color;

import misc.Direction;

public class MoveableSource extends Source {

	private static final long serialVersionUID = -5430284811313280074L;

	public MoveableSource(Color color, Direction direction) {
		super(color, direction);
	}
	
	public boolean isFixed() {
		return false;
	}


	
}
