package tests;

import static org.junit.Assert.assertEquals;
import exceptions.PositionOutOfBoundsException;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;
import exceptions.TileIsFixedException;
import game.Board;
import misc.Direction;
import misc.Position;

import org.junit.Before;
import org.junit.Test;

import tiles.EmptyTile;
import tiles.SimpleMirror;
import tiles.Wall;

public class BoardTest {

	private Board board;

	@Before
	public void init() {
		board = new Board(10, 10);
	}

	@Test
	public void testBlankBoard() {
		assertEquals(EmptyTile.class, board.getTile(new Position(0, 0))
				.getClass());
	}

	@Test
	public void testTileAccessors() {
		board.setTile(new Position(1, 0), new Wall());
		assertEquals(Wall.class, board.getTile(new Position(1, 0)).getClass());
	}

	@Test
	public void testMoveTile() throws TileIsFixedException,
			SourceTileEmptyException, TargetTileNotEmptyException {

		Position p1 = new Position(2, 0);
		Position p2 = new Position(2, 1);

		board.setTile(p1, new SimpleMirror(Direction.NORTH));
		board.moveTile(p1, p2);
		assertEquals(EmptyTile.class, board.getTile(p1).getClass());
		assertEquals(SimpleMirror.class, board.getTile(p2).getClass());
	}

	@Test(expected = TargetTileNotEmptyException.class)
	public void testMoveTargetNotEmpty() throws TileIsFixedException,
			SourceTileEmptyException, TargetTileNotEmptyException {

		Position source = new Position(0, 0);
		Position target = new Position(0, 1);

		board.setTile(source, new SimpleMirror(Direction.NORTH));
		board.setTile(target, new SimpleMirror(Direction.NORTH));
		board.moveTile(source, target);
	}

	@Test(expected = TileIsFixedException.class)
	public void testMoveFixedTile() throws TileIsFixedException,
			SourceTileEmptyException, TargetTileNotEmptyException {

		Position source = new Position(0, 0);

		board.setTile(source, new Wall());
		board.moveTile(source, new Position(1, 1));
	}

	@Test(expected = PositionOutOfBoundsException.class)
	public void testSetInvalidPosition() throws SourceTileEmptyException,
			TargetTileNotEmptyException {

		Position p = new Position(10, 10);
		board.setTile(p, new Wall());
	}

	@Test(expected = PositionOutOfBoundsException.class)
	public void testGetInvalidPosition() throws SourceTileEmptyException,
			TargetTileNotEmptyException {

		Position p = new Position(10, 10);
		board.getTile(p);
	}
}
