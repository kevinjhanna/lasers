package parser;

import java.awt.Color;

import misc.Direction;

import tiles.DoubleMirror;
import tiles.Filter;
import tiles.MoveableSource;
import tiles.SimpleMirror;
import tiles.Source;
import tiles.SplitMirror;
import tiles.Target;
import tiles.Tile;
import tiles.Wall;

/**
 * Enumerative that maps numeric values from board files to tiles and performs
 * basic validations.
 */
public enum TileValue {
	SOURCE(1, 3, true), MOVEABLESOURCE(2, 3, true), TARGET(3, 0, true), SIMPLEMIRROR(
			4, 3, false), DOUBLEMIRROR(5, 1, false), SPLITMIRROR(6, 1, false), WALL(
			7, 0, false), FILTER(8, 1, true);

	private final int n;
	private final int directions;
	private final boolean hasColor;

	TileValue(int n, int directions, boolean hasColor) {
		this.n = n;
		this.directions = directions;
		this.hasColor = hasColor;
	}

	public static TileValue fromInt(int n) {
		TileValue[] values = TileValue.values();
		for (TileValue value : values) {
			if (value.n == n) {
				return value;
			}
		}
		return null;
	}

	public boolean validDirection(int r) {
		return (r >= 0 && r <= directions);
	}

	public boolean validColor(int c) {
		return (!hasColor && c == 0) || (hasColor && c >= 0 && c <= 255);
	}
	
	public static Tile generateTileObject(TileValue mockTile, Color color, Direction direction){
		Tile realTile = null;
		switch (mockTile) {
		case SOURCE:
			realTile = new Source(color, direction);
			break;
		case MOVEABLESOURCE:
			realTile = new MoveableSource(color, direction);
			break;
		case TARGET:
			realTile = new Target(color);
			break;
		case SIMPLEMIRROR:
			realTile = new SimpleMirror(direction);
			break;
		case DOUBLEMIRROR:
			realTile = new DoubleMirror(direction);
			break;
		case SPLITMIRROR:
			realTile = new SplitMirror(direction);
			break;
		case WALL:
			realTile = new Wall();
			break;
		case FILTER:
			realTile = new Filter(color, direction);
			break;
		}
		return realTile;
	}
	
}
