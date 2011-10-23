package tests;

import game.Board;
import game.InvalidBoardSizeException;
import junit.framework.TestCase;

import misc.Position;

import org.junit.Test;

import tiles.EmptyTile;

public class BoardTest extends TestCase {

	private Board board;

	public void setUp() {
		board = new Board(10, 10);
	}

	@Test
	public void testBlankBoard() {
		assertTrue(board.getTile(new Position(0, 0)).getClass() == EmptyTile.class);
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
}
