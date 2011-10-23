package tiles;

import game.TileDrawer;

public interface Drawable {

	public <T> T draw(TileDrawer<T> drawer);
	
}
