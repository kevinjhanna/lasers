package frontend;

import game.Beam;
import game.Cell;
import game.Drawable;
import game.Ray;
import gui.ImageUtils;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import misc.Direction;
import tiles.DoubleMirror;
import tiles.Filter;
import tiles.FixedSource;
import tiles.SimpleMirror;
import tiles.Source;
import tiles.SplitMirror;
import tiles.Target;
import tiles.Wall;

/**
 * Class responsible for drawing the content of the board cells.
 * 
 * @see Drawable
 */
public class ImageDrawer {

	private static final Color MASK_COLOR = new Color(0, 0, 255);

	private Map<String, Image> images;

	public ImageDrawer() {
		loadResources();
	}

	private void loadResources() {
		try {
			images = new HashMap<String, Image>();

			Image source = ImageUtils.loadImage("resources/source.png");
			Image target = ImageUtils.loadImage("resources/target.png");
			Image wall = ImageUtils.loadImage("resources/wall.png");
			Image filter = ImageUtils.loadImage("resources/filter.png");
			Image simpleMirror = ImageUtils
					.loadImage("resources/simple-mirror.png");
			Image doubleMirror = ImageUtils
					.loadImage("resources/double-mirror.png");
			Image splitMirror = ImageUtils
					.loadImage("resources/split-mirror.png");
			Image ray = ImageUtils.loadImage("resources/half-laser.png");

			images.put(FixedSource.class.getName(), source);
			images.put(Source.class.getName(), source);
			images.put(Target.class.getName(), target);
			images.put(Wall.class.getName(), wall);
			images.put(Filter.class.getName(), filter);
			images.put(SimpleMirror.class.getName(), simpleMirror);
			images.put(DoubleMirror.class.getName(), doubleMirror);
			images.put(SplitMirror.class.getName(), splitMirror);
			images.put(Ray.class.getName(), ray);

		} catch (IOException e) {
			System.out.println("Error: Could not load images.");
			System.exit(0);
		}
	}

	public List<Image> draw(Cell cell) {
		if (cell == null) {
			throw new IllegalArgumentException();
		}

		List<Image> layers = new ArrayList<Image>();
		drawBeams(cell, layers);
		layers.add(draw((Drawable) cell));

		return layers;
	}

	private Image draw(Drawable drawable) {
		Image image = images.get(drawable.getClass().getName());

		Color color = drawable.getColor();
		if (color != null) {
			image = replaceColor(image, color);
		}

		Direction direction = drawable.getDirection();
		if (direction != null) {
			image = rotate(image, direction);
		}
		return image;
	}

	private void drawBeams(Cell cell, List<Image> layers) {
		Direction[] directions = { Direction.EAST, Direction.WEST,
				Direction.NORTH, Direction.SOUTH, };

		for (Direction d : directions) {
			Beam beam = cell.getBeam(d);
			if (beam != null) {
				layers.add(draw(beam));
			}
		}
	}

	private Image replaceColor(Image img, Color color) {
		return ImageUtils.replaceColor(img, MASK_COLOR, color);
	}

	private Image rotate(Image img, Direction direction) {
		return ImageUtils.rotateImage(img, direction.ordinal());
	}
}
