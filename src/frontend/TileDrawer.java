package frontend;

import java.awt.Color;

import misc.Direction;

public interface TileDrawer<T> {

	T draw(String base);
	
	T withColor(T t, Color color);
	
	T withDirection(T t, Direction direction);
}