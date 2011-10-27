package game;

import java.awt.Color;
import misc.*;

public class Ray {
	private Board board;
	private Position position;
	private Direction direction;
	private Color color;

	public Ray(Board board, Position position, Direction direction, Color color) {
		this.board = board;
		this.position = position;
		this.direction = direction;
		this.color = color;
		move(direction, color);
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
			System.out.println(position + " -> " + direction.name());
			hit();			
		}
	}

	public void bifurcate(Direction d1, Color c1, Direction d2, Color c2) {
		new Ray(board, position, d2, c2);
		move(d1, c1);
	}
	
	public void bifurcate(Direction d1, Direction d2) {
		new Ray(board, position, d2, color);
		move(d1);
	}

	private void hit() {
		board.getTile(position).hit(this);
	}
	
	public Color getColor() {
		return color;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
