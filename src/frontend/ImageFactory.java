package frontend;
import gui.ImageUtils;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tiles.*;

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

	public Image forTile(Tile t) {
		Image img = images.get(t.getClass().getName());
		if (img == null) {
			return null;
		}
		
		if (t instanceof ColoredTile) {
			img = ImageUtils.replaceColor(img, maskColor, ((ColoredTile) t).getColor());
		}
		if (t instanceof Rotatable) {
			img = ImageUtils.rotateImage(img, ((Rotatable) t).getOrientation().ordinal());
		}
		return img;
	}
}
