package gameobjects;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class FunkyPlanet {
	private PVector position;
	private int planetWidth;
	private int planetHeight;

	
	/**
	 * Constructor
	 * @param position
	 * @param planetWidth
	 * @param planetHeight
	 * @param planetColor
	 */
	public FunkyPlanet(PVector position, int planetWidth, int planetHeight) {
		this.position = position;
		this.planetWidth = planetWidth;
		this.planetHeight = planetHeight;
	}

	/**
	 * draws the Planets, has to be implemented by the child classes
	 */
	public abstract void drawPlanet(PApplet view);

	/**
	 * getter for the position
	 * @return
	 */
	public PVector getPosition() {
		return position;
	}

	/**
	 * getter for the width of the planet
	 * @return
	 */
	public int getPlanetWidth() {
		return planetWidth;
	}

	/**
	 * getter for the height of the planet
	 * @return
	 */
	public int getPlanetHeight() {
		return planetHeight;
	}

}
