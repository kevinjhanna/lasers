package frontend;

import tiles.Drawable;

public interface Drawer<T> {

	public Iterable<T> draw(Drawable drawable);
	
}