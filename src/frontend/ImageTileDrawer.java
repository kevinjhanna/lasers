package frontend;

import game.TileDrawer;
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
			
			Image source       = ImageUtils.loadImage("resources/source.png");
			Image target       = ImageUtils.loadImage("resources/target.png");
			Image wall         = ImageUtils.loadImage("resources/wall.png");
			Image filter       = ImageUtils.loadImage("resources/filter.png");
			Image simpleMirror = ImageUtils.loadImage("resources/simple-mirror.png");
			Image doubleMirror = ImageUtils.loadImage("resources/double-mirror.png");
			Image splitMirror  = ImageUtils.loadImage("resources/split-mirror.png");
			
			images.put(Source.class.getName(), source);
			images.put(MoveableSource.class.getName(), source);
			images.put(Target.class.getName(), target);
			images.put(Wall.class.getName(), wall);
			images.put(Filter.class.getName(), filter);
			images.put(SimpleMirror.class.getName(), simpleMirror);
			images.put(DoubleMirror.class.getName(), doubleMirror);
			images.put(SplitMirror.class.getName(), splitMirror);
			
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
