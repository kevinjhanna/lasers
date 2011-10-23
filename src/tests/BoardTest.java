package tests;

import game.Board;
import game.InvalidBoardSizeException;
import junit.framework.TestCase;
import misc.Position;

import org.junit.Test;

import tiles.EmptyTile;
import tiles.Wall;

public class BoardTest extends TestCase {

	private Board board;

	public void setUp() {
		board = new Board(10, 10);
	}

	@Test
	public void testBlankBoard() {
		assertEquals(EmptyTile.class, board.getTile(new Position(0, 0)).getClass());
	}

	@Test
	public void testInvalidBoardSize() {
		try {
			new Board(4, 4);
			fail();
		} catch (InvalidBoardSizeException e) {
		}

		try {
			new Board(26, 26);
			fail();
		} catch (InvalidBoardSizeException e) {
		}
	}
	
	@Test
	public void testTileAccessors() {
		board.setTile(new Position(1, 0), new Wall());
		assertEquals(Wall.class, board.getTile(new Position(1, 0)).getClass());		
	}
	
	@Test
	public void testMoveTile() {
		board.setTile(new Position(2, 0), new Wall());
		try {
			board.moveTile(new Position(2, 0), new Position(2, 1));
			assertEquals(EmptyTile.class, board.getTile(new Position(2, 0)).getClass());
			assertEquals(Wall.class, board.getTile(new Position(2, 1)).getClass());
		} catch (Exception e) {
			fail();
		}
	}
}
