package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import game.Ray;

import java.awt.Color;
import java.util.Stack;

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
		Stack<Ray> bifurcations = new Stack<Ray>();
		propagation.process(new Ray(Color.CYAN, Direction.EAST), bifurcations);
		assertFalse(propagation.hasRays());
		assertTrue(bifurcations.isEmpty());
	}
	
	@Test
	public void testHasRayColor() {
		Ray ray = new Ray(Color.CYAN, Direction.EAST);
		propagation.process(ray, new Stack<Ray>());
		assertFalse(propagation.hasRay(Color.CYAN));
	}
}
