package tiles;

import game.Ray;
import gui.ImageUtils;

import java.awt.Color;

public class FilterPropagation extends PropagationComponent {

	public FilterPropagation(Tile tile) {
		super(tile);
	}

	public void process(Ray ray) {
		setOrigin(ray);
		if (!ray.getDirection().equalsIgnoreSense(getDirection())) {
			Color filter = ImageUtils.filter(ray.getColor(), getColor());
			ray.setColor(filter);
			setRay(ray.getDirection(), ray);
			ray.move();
		}
	}

}
