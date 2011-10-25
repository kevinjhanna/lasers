package game;
import java.awt.Color;
import misc.*;

public class Ray {
	private Direction direction;
	private Color color;
	private boolean moving;
	
	public Ray(Direction d, Color c){
		direction = d;
		color = c;
		moving = true;
	}
	
	public Ray bifurcate(){
		this.keepMoving();
		return new Ray(direction.turn(), color);
	}
	
	public void keepMoving(){
		
	}
	
	public void changeColor(){
		
	}
	
	public void stop(){
		moving = false;
	}
	
}
