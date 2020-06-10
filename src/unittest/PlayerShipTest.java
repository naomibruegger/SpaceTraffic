package unittest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import controller.GameController;

import static org.mockito.Mockito.*;

import gameobjects.PlayerShip;
import processing.core.PApplet;
import processing.core.PVector;

public class PlayerShipTest {

	PVector position = new PVector(250, 250);
	int shipWidth = 80;
	int shipHeight = 80;
	int shipColor = 0xFFFFFF;
	int shipSpeed = 5;
	int health = 5;
	

	/**
	 * Constructor test
	 */
	@Test
	public void testCtor() {
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, shipSpeed);
		assertEquals(position, ship.getPosition());
		assertEquals(shipWidth, ship.getShipWidth(), 0.0);
		assertEquals(shipHeight, ship.getShipHeight(), 0.0);
		assertEquals(health, ship.getPlayerHealth(), 0.0);
		assertEquals(shipColor, ship.getShipColor());
		assertEquals(shipSpeed, ship.getShipSpeed(), 0.0);
	}
	
	/**
	 * Testing getter for the shipSpeed
	 */
	@Test
	public void testGetShipSpeed() {
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, 7);
		assertEquals(7, ship.getShipSpeed(), 0.0);
	}

	/**
	 * Testing if the upScore method counts up the playerScore
	 */
	@Test
	public void testScoreUp() {
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, shipSpeed);
		ship.upScore();
		ship.upScore();
		assertEquals(2, ship.getPlayerScore(), 0.0);
	}
	
	/**
	 * Testing if the downHealth method counts the health down
	 */
	@Test
	public void testDownHealth() {
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, shipSpeed);
		ship.downHealth();
		ship.downHealth();
		assertEquals(3, ship.getPlayerHealth(), 0.0);
	}
	
	/**
	 * Testing getter for health
	 */
	@Test
	public void testGetPlayerHealth() {
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, 25, shipColor, shipSpeed);
		assertEquals(25, ship.getPlayerHealth(), 0.0);
	}
	
	/**
	 * Testing if the ship moves up according to the shipSpeed
	 */
	@Test
	public void testMoveUp() {
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, shipSpeed);
		ship.movementUp();
		assertEquals(245, position.y, 0.0);
	}
	
	/**
	 * testing if the ship moves down according to the shipSpeed
	 */
	@Test
	public void testMoveDown() {
		GameController mockView = mock(GameController.class);
		when(mockView.getDisplayWidth()).thenReturn((int)position.y);
		
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, shipSpeed);
		ship.movementDown(mockView);
		assertEquals(255, mockView.getDisplayWidth(), 0.0);
	}
	
	/**
	 * testing if the ship moves right according to the shipSpeed
	 */
	@Test
	public void testMoveRight() {
		GameController mockView = mock(GameController.class);
		when(mockView.getDisplayWidth()).thenReturn((int)position.x);

		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, shipSpeed);
		ship.movementRight(mockView);
		assertEquals(255, mockView.getDisplayWidth(), 0.0);
	}
	
	/**
	 * testing if the ship moves left according to the shipSpeed
	 */
	@Test
	public void testMoveLeft() {
		PlayerShip ship = new PlayerShip(position, shipWidth, shipHeight, health, shipColor, shipSpeed);
		ship.movementLeft();
		assertEquals(245, position.x, 0.0);
	}

}
