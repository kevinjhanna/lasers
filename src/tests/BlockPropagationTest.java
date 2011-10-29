package tests;

import static org.junit.Assert.assertFalse;
import game.Ray;

import java.awt.Color;

import misc.Direction;

import org.junit.Before;
import org.junit.Test;

import tiles.Wall;
import tiles.propagation.BlockPropagation;

public class BlockPropagationTest {

	private BlockPropagation propagation;
	
	@Before
	public void init() {
		propagation = new BlockPropagation(new Wall());
	}
	
	@Test
	public void testHasRays() {
		assertFalse(propagation.hasRays());
		propagation.process(new Ray(Color.CYAN, Direction.EAST));
		assertFalse(propagation.hasRays());
	}
	
	@Test
	public void testHasRayColor() {
		Ray ray = new Ray(Color.CYAN, Direction.EAST);
		propagation.process(ray);
		assertFalse(propagation.hasRay(Color.CYAN));
	}
}
