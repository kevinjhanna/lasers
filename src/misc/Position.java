package misc;

import java.io.Serializable;

/**
 * Class that represents a position in the board
 */
public class Position implements Serializable {

	private static final long serialVersionUID = 8918404612043676881L;

	public final int row;
	public final int column;

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public Position move(Direction d) {
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

	public String toString() {
		return "Position [row=" + row + ", column=" + column + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + row;
		result = prime * result + column;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (row != other.row)
			return false;
		if (column != other.column)
			return false;
		return true;
	}
}
