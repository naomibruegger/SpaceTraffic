package gameobjects;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Class for implementing the PlayerShip (extends the SpaceShip-Class)
 * @author Naomi Brügger
 *
 */
public class PlayerShip extends SpaceShip {
		private PVector position;
		private int playerScore;
		private int health;
		private int shipSpeed;

	
	/**
	 * Constructor for the PlayerShip
	 * @param position
	 * @param shipWidth
	 * @param shipHeight
	 * @param health
	 * @param shipColor
	 * @param shipSpeed
	 */
	public PlayerShip(PVector position, int shipWidth, int shipHeight, int health, int shipColor,
			int shipSpeed) {
		super(position, shipWidth, shipHeight, shipColor);
		this.health = health;
		this.position = position;
		this.shipSpeed = shipSpeed;
	}
	

	/**
	 * getter for shipSpeed
	 * @return
	 */
	public int getShipSpeed() {
		return shipSpeed;
	}

	
	/**
	 * counts up the playerScore
	 */
	public void upScore() {
		playerScore++;
	}
	
	/**
	 * getter for the playerScore
	 * @return 
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	
	/**
	 * getter for the playerHealth
	 * @return
	 */
	public int getPlayerHealth() {
		return health;
	}
	
	/**
	 * counts down the players health
	 */
	public void downHealth() {
		health--;
	}

	/**
	 * Going up with the arrow key
	 */
	public void movementUp() {
		position.y = Math.max(position.y - getShipSpeed(), 0);
	}
	
	/**
	 * Going down with the arrow key
	 * @param view
	 */
	public void movementDown(PApplet view) {
		position.y = Math.min(position.y + getShipSpeed(), view.height);
	}

	/**
	 * Going right with the arrow key
	 * @param view
	 */
	public void movementRight(PApplet view) {
		position.x = Math.min(position.x + getShipSpeed(), view.width);
	}
	
	/**
	 * Going left with the arrow key
	 */
	public void movementLeft() {
		position.x = Math.max(position.x - getShipSpeed(), 0);
	}
	
	/**
	 * drawing the PlayerShip, has to be implemented
	 */
	@Override
	public void drawShip(PApplet view) {
		float x = position.x;
		float y = position.y;
			
		view.fill(getShipColor());
		view.ellipse(x, y, getShipWidth(), getShipHeight());
		view.fill(0x00000000);
		view.stroke(0xFFFFFFFF);
		view.ellipse(x - getShipHeight()/25, y + getShipWidth()/7, getShipHeight() - 25, getShipHeight() - 5);
		}

}
