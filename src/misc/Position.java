package misc;

import java.io.Serializable;

/**
 * Class that represents a position in the board
 */
public class Position implements Serializable {

	private static final long serialVersionUID = 8918404612043676881L;

	public final int row;
	public final int column;

	/**
	 * Creates a new position
	 * 
	 * @param row
	 * @param column
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Returns a new position displaced from the receiver in the specified
	 * direction.
	 */
	public Position displace(Direction d) {
		switch (d) {
		case NORTH:
			return new Position(row - 1, column);
		case EAST:
			return new Position(row, column + 1);
		case SOUTH:
			return new Position(row + 1, column);
		case WEST:
			return new Position(row, column - 1);
		}
		return null;
	}

	/**
	 * Returns a string representation of a direction.
	 */
	public String toString() {
		return "Position [row=" + row + ", column=" + column + "]";
	}

}
