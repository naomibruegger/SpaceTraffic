package gameobjects;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Class for implementing a SpaceShip (child classes are PlayerShip and EnemyShip)
 * @author Naomi Brügger
 *
 */
public abstract class SpaceShip {
	private PVector position;
	private int shipWidth;
	private int shipHeight;
	private int shipColor;

	/**
	 * Constructor
	 * @param position
	 * @param shipWidth
	 * @param shipHeight
	 * @param health
	 * @param shipColor
	 * @param shipSpeed
	 */
	public SpaceShip(PVector position, int shipWidth, int shipHeight, int shipColor) {
		this.position = position;
		this.shipWidth = shipWidth;
		this.shipHeight = shipHeight;
		this.shipColor = shipColor;
	}
	
	
	/**
	 * draws the SpaceShips, has to be implemented by the child classes
	 */
	public abstract void drawShip(PApplet view);

	/**
	 * getter for shipColor
	 * @return
	 */
	public int getShipColor() {
		return shipColor;
	}

	/**
	 * getter for shipWidth
	 * @return
	 */
	public int getShipWidth() {
		return shipWidth;
	}

	/**
	 * getter for shipHeight
	 * @return
	 */
	public int getShipHeight() {
		return shipHeight;
	}

	/**
	 * getter for the position of the ship
	 * @return
	 */
	public PVector getPosition() {
		return position;
	}

}
