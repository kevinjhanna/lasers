package frontend;

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
import tiles.Drawable;
import tiles.DrawableLayer;
import tiles.Filter;
import tiles.Source;
import tiles.SimpleMirror;
import tiles.FixedSource;
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

	public List<Image> draw(Drawable drawable) {
		if (drawable == null) {
			throw new IllegalArgumentException();
		}

		List<Image> layers = new ArrayList<Image>();

		Iterable<DrawableLayer> underlay = drawable.getUnderlay();
		if (underlay != null) {
			for (DrawableLayer layer : underlay) {
				if (layer != null) {
					layers.add(drawLayer(layer));
				}
			}
		}

		layers.add(drawLayer(drawable));

		Iterable<DrawableLayer> overlay = drawable.getOverlay();
		if (overlay != null) {
			for (DrawableLayer layer : overlay) {
				if (layer != null) {
					layers.add(drawLayer(layer));
				}
			}
		}
		return layers;
	}

	private Image drawLayer(DrawableLayer drawable) {
		Image image = images.get(drawable.getClass().getName());

		Color color = drawable.getColor();
		if (color != null) {
			image = ImageUtils.replaceColor(image, MASK_COLOR, color);
		}

		Direction direction = drawable.getDirection();
		if (direction != null) {
			image = ImageUtils.rotateImage(image, direction.ordinal());
		}
		return image;
	}

	public Image withColor(Image img, Color color) {
		return ImageUtils.replaceColor(img, MASK_COLOR, color);
	}

	public Image withDirection(Image img, Direction direction) {
		return ImageUtils.rotateImage(img, direction.ordinal());
	}
}
