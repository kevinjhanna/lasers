package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.Game;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import misc.Direction;
import misc.Position;

import org.junit.Before;
import org.junit.Test;

import tiles.SimpleMirror;
import tiles.FixedSource;
import tiles.Tile;
import tiles.Wall;

public class GameTest {

	private Game g;
	
	@Before
	public void init() {
		Map<Tile, Position> tiles = new HashMap<Tile, Position>();
		tiles.put(new Wall(), new Position(0, 0));
		tiles.put(new SimpleMirror(Direction.EAST), new Position(1, 1));
		tiles.put(new FixedSource(Color.RED, Direction.EAST), new Position(2, 0));
		
		g = new Game(10, 15, tiles);
		g.start(new NullObserver());
	}
	
	@Test
	public void testTileMethods() {
		assertTrue(g.isFixed(0, 0));
		assertFalse(g.isFixed(1, 1));
		assertFalse(g.canRotate(0, 0));
		assertTrue(g.canRotate(1, 1));
	}
	
	@Test
	public void testRestart() {
		g.restart();
		assertTrue(g.isFixed(0, 0));
	}
	
	@Test
	public void testBoardMethods() {
		assertTrue(g.getBoardHeight() == 10);
		assertTrue(g.getBoardWidth() == 15);
	}
	
	@Test
	public void testScore() {
		assertTrue(g.getScore() == 14);
		
	}
	
}
