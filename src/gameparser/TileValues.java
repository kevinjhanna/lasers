package gameparser;

import misc.Direction;

public enum TileValues {
	SOURCE(1, 3, true), MOVEABLESOURCE(2,3, true), TARGET(3,0, true), SIMPLEMIRROR(4,3, false), DOUBLEMIRROR(5,1, false), SPLITMIRROR(6,1, false), WALL(7,0, false), FILTER(8,1, true);
	
	final int type;
	final int possibleRotation;
	public final boolean canColor;

	TileValues(int t, int r, boolean c){
		type = t;
		possibleRotation = r;
		canColor = c;
	}
	

	
	public static TileValues fromInt(int n) {
		TileValues[] values = TileValues.values();
		return values[n - 1];
	}
	
	public boolean possibleRotation(int r){
		return (r >= 0 && r <= possibleRotation);
	}
	
	public boolean validColor(int c){
		return (!canColor && c == 0) || (canColor && c >= 0 && c <= 250);
	}
}
