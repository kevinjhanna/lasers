package tiles.propagation;

import game.Ray;
import gui.ImageUtils;

import java.awt.Color;
import java.util.Stack;

import tiles.Tile;

/**
 * Propagation component that filters the incoming ray color.
 */
public class FilterPropagation extends PropagationComponent {

	private static final long serialVersionUID = 4109111052205979814L;

	public FilterPropagation(Tile tile) {
		super(tile);
	}

	public void process(Ray ray, Stack<Ray> bifurcations) {
		setOrigin(ray);
		if (!ray.getDirection().equalsIgnoreSense(getDirection())) {
			Color filter = ImageUtils.filter(ray.getColor(), getColor());
			ray.setColor(filter);
			setBeam(ray.getDirection(), ray);
		} else {
			ray.stop();
		}
	}

}
