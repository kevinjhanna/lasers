import gui.ImageUtils;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tiles.ColoredTile;
import tiles.Rotatable;
import tiles.Tile;

class ImageFactory {

	private static final ImageFactory instance = new ImageFactory();
	private static final Color maskColor = new Color(0, 0, 255);
	
	private Map<String, Image> images;
	
	public static ImageFactory getInstance() {
		return instance;
	}

	private ImageFactory() {
		try {
			images = new HashMap<String, Image>();
			images.put("Wall",
					ImageUtils.loadImage("resources/wall.png"));
			images.put("DoubleMirror",
					ImageUtils.loadImage("resources/double-mirror.png"));
			images.put("Filter",
					ImageUtils.loadImage("resources/filter.png"));
			images.put("HalfLaser",
					ImageUtils.loadImage("resources/half-laser.png"));
			images.put("Laser",
					ImageUtils.loadImage("resources/laser.png"));
			images.put("SimpleMirror",
					ImageUtils.loadImage("resources/simple-mirror.png"));
			images.put("Source",
					ImageUtils.loadImage("resources/source.png"));
			images.put("SplitMirror",
					ImageUtils.loadImage("resources/split-mirror.png"));
			images.put("Target",
					ImageUtils.loadImage("resources/target.png"));
		} catch (IOException e) {
			System.out.println("Could not load images.");
			System.exit(0);
		}
	}

	public Image tile(Tile t) {
		Image img = images.get(t.getName());
		if (t instanceof ColoredTile) {
			img = ImageUtils.replaceColor(img, maskColor, ((ColoredTile) t).getColor());
		}
		if (t instanceof Rotatable) {
			img = ImageUtils.rotateImage(img, ((Rotatable) t).getOrientation().ordinal());
		}
		return img;
	}
}
