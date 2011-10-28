package game;

import java.awt.Color;

import misc.Direction;
import misc.Position;
import tiles.DrawableLayer;

public class Ray implements DrawableLayer, Cloneable {

	private Board board;
	private Position position;
	private Direction direction;
	private Color color;

	public Ray(Board board, Position position, Direction direction, Color color) {
		this.board = board;
		this.position = position;
		this.direction = direction;
		this.color = color;
	}

	private void move(Direction direction, Color color) {
		this.color = color;
		move(direction);
	}

	public void move(Direction direction) {
		this.direction = direction;
		move();
	}

	public void move() {
		position = position.move(direction);
		if (board.validPosition(position)) {
			hit();
		}
	}

	public void bifurcate(Direction d1, Color c1, Direction d2, Color c2) {
		new Ray(board, position, d2, c2);
		move(d1, c1);
	}

	public void bifurcate(Direction d1, Direction d2) {
		if (d1 == null || d2 == null) {
			throw new IllegalArgumentException();
		}
		new Ray(board, position, d2, color).move();
		move(d1);
	}

	private void hit() {
		board.getTile(position).hit(this);
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Ray clone() {
		return new Ray(board, position, direction, color);
	}

}
