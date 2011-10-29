package misc;

import java.io.Serializable;

/**
 * A position in a board.
 */
public class Position implements Serializable {

	private static final long serialVersionUID = 8918404612043676881L;

	public final int row;
	public final int column;

	/**
	 * Creates a new position.
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
