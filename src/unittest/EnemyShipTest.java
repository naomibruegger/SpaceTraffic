package unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import gameobjects.EnemyShip;
import processing.core.PVector;

public class EnemyShipTest {

	PVector position = new PVector(250, 250);
	int shipWidth = 80;
	int shipHeight = 80;
	int shipColor = 0xFFFFFF;
	
	/**
	 * Constructor test
	 */
	@Test
	public void testCtor() {
		EnemyShip ship = new EnemyShip(position, shipWidth, shipHeight, shipColor);
		assertEquals(position, ship.getPosition());
		assertEquals(shipWidth, ship.getShipWidth(), 0.0);
		assertEquals(shipHeight, ship.getShipHeight(), 0.0);
		assertEquals(shipColor, ship.getShipColor());
	}

}
