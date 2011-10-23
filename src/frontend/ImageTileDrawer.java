package frontend;

import gui.ImageUtils;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import misc.Direction;

import tiles.*;

public class ImageTileDrawer implements TileDrawer<Image> {

	private static final Color maskColor = new Color(0, 0, 255);

	private Map<String, Image> images;

	public ImageTileDrawer() {
		loadResources();
	}
	
	private void loadResources() {
		try {
			images = new HashMap<String, Image>();
			images.put(Wall.class.getName(),
					ImageUtils.loadImage("resources/wall.png"));
			images.put(DoubleMirror.class.getName(),
					ImageUtils.loadImage("resources/double-mirror.png"));
			images.put(Filter.class.getName(),
					ImageUtils.loadImage("resources/filter.png"));
			images.put(SimpleMirror.class.getName(),
					ImageUtils.loadImage("resources/simple-mirror.png"));
			images.put(Source.class.getName(),
					ImageUtils.loadImage("resources/source.png"));
			images.put(SplitMirror.class.getName(),
					ImageUtils.loadImage("resources/split-mirror.png"));
			images.put(Target.class.getName(),
					ImageUtils.loadImage("resources/target.png"));
		} catch (IOException e) {
			System.out.println("Error: Could not load images.");
			System.exit(0);
		}
	}

	public Image draw(String base) {
		return images.get(base);
	}

	public Image withColor(Image img, Color color) {
		return ImageUtils.replaceColor(img, maskColor, color);
	}

	public Image withDirection(Image img, Direction direction) {
		return ImageUtils.rotateImage(img, direction.ordinal());
	}
}
