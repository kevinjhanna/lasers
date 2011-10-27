package tiles;

import game.Ray;

import java.awt.Color;

import misc.Direction;

public class RayComponent {
	
	private Color[] rays = new Color[4];
	
	protected final void moveRay(Ray ray, Direction direction) {
		setRay(ray.getDirection(), ray.getColor());
		setRay(ray.getDirection().getOpposite(), ray.getColor());
		ray.move(direction);
	}
	
	protected final void bifurcateRay(Ray ray, Direction d1, Color c1, Direction d2, Color c2) {
		setRay(ray.getDirection(), ray.getColor());
		setRay(d1, c1);
		setRay(d2, c2);
		ray.bifurcate(d1, d2);
	}
	
	protected final void bifurcateRay(Ray ray, Direction d1, Direction d2) {
		setRay(ray.getDirection(), ray.getColor());
		setRay(d1, ray.getColor());
		setRay(d2, ray.getColor());
		ray.bifurcate(d1, d2);
	}
	
	public void clearRays() {
		for (int i = 0; i < 4; i++) {
			rays[i] = null;
		}
	}

	protected final void setRay(Direction d, Color c) {
		rays[d.ordinal()] = c;
	}
	
	protected final Color getRay(Direction d) {
		return rays[d.ordinal()];
	}
}
