package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import gameobjects.PointPlanet;
import processing.core.PVector;

public class PointPlanetTest {
	
	PVector position = new PVector(50, 50);
	int planetWidth = 60;
	int planetHeight = 60;
	int points = 5;

	/**
	 * Constructor test
	 */
	@Test
	public void test() {
		PointPlanet planet = new PointPlanet(position, planetWidth, planetHeight, points);
		assertEquals(position, planet.getPosition());
		assertEquals(planetWidth, planet.getPlanetWidth(), 0.0);
		assertEquals(planetHeight, planet.getPlanetHeight(), 0.0);
		assertEquals(points, planet.getPoints(), 0.0);
	}
	
	/**
	 * testing getter for points
	 */
	@Test
	public void testGetPoints() {
		PointPlanet planet = new PointPlanet(position, planetWidth, planetHeight, 10);
		assertEquals(10, planet.getPoints(), 0.0);
	}
}
