package gameobjects;

import processing.core.PApplet;
import processing.core.PVector;

public class PointPlanet extends FunkyPlanet {
	private int points;
	private PVector position;

	public PointPlanet(PVector position, int planetWidth, int planetHeight, int points) {
		super(position, planetWidth, planetHeight);
		this.points = points;
		this.position = position;
	}

	/**
	 * getter for the points
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * draws the Planet, has to be implemented
	 */
	@Override
	public void drawPlanet(PApplet view) {
		float x = position.x;
		float y = position.y;
		
		view.fill(view.random(255), view.random(255), view.random(255), view.random(255));
		view.ellipseMode(view.CENTER);
		view.ellipse(x, y, getPlanetWidth(), getPlanetHeight());
	}

}
