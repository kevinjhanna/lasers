package tiles;

import game.Ray;

public class IgnorePropagation extends PropagationComponent {
	
	protected IgnorePropagation(Tile tile) {
		super(tile);
	}

	public void process(Ray ray) {
		setOrigin(ray);
		setRay(ray.getDirection(), ray);
		ray.move();
	}

}
