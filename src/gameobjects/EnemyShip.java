package gameobjects;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Class for implementing the EnemyShip (extends the SpaceShip-Class)
 * @author Naomi Brügger
 *
 */
public class EnemyShip extends SpaceShip{
	private PVector position;
	/**
	 * Constructor for the EnemyShip
	 * @param position
	 * @param shipWidth
	 * @param shipHeight
	 * @param shipColor
	 */
	public EnemyShip(PVector position, int shipWidth, int shipHeight, int shipColor) {
		super(position, shipWidth, shipHeight, shipColor);
		this.position = position;
	}

	/**
	 * drawing the EnemyShip, has to be implemented
	 */
	@Override
	public void drawShip(PApplet view) {
		float x = position.x;
		float y = position.y;
			
		view.fill(getShipColor());
		view.ellipse(x, y, getShipWidth(), getShipHeight());
		view.fill(0x00000000);
		view.stroke(0xFFFFFFFF);
		view.ellipse(x - getShipHeight()/25, y - getShipWidth()/7, getShipHeight() - 25, getShipHeight() - 5);
	}

}
