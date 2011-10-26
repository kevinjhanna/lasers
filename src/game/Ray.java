package game;
import java.awt.Color;
import misc.*;

public class Ray {
	private Direction direction;
	private Color color;
	private Position position;
	private boolean moving;
	private Ray generatedRay = null;
	
	public Ray(Position p, Direction d, Color c){
		position = p;
		direction = d;
		color = c;
		moving = true;
	}
	
	public void bifurcate(){
		// the generated ray one step ahead
		generatedRay = new Ray(new Position(direction.turn().row + position.row, direction.turn().column + position.column), direction.turn(), color);
		this.moveStraight();
		System.out.println("It's time to bifurcate, baby!");
	}
	
	public boolean hasGeneratedRay(){
		return generatedRay != null;
	}
	
	public Ray getGeneratedRay(){
		Ray ray = generatedRay;
		generatedRay = null;
		return ray;
	}
	
	public void moveStraight(){
		position = new Position(position.row + direction.row, position.column + direction.column);
	}
	
	public void reflect(){
		direction = direction.turn(3);
		moveStraight();
		System.out.println("Turns!");
	}
	
	public void changeColor(){
		
	}
	
	public void stopMovement(){
		moving = false;
	}
	


	public Position getPosition(){
		return position;
	}
	
	public void setPosition(Position p){
		position = p;
	}
	
	public boolean canReact(){
		return moving;
	}
}
