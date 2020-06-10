package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import gameobjects.EnemyShip;
import gameobjects.FunkyPlanet;
import gameobjects.PlayerShip;
import gameobjects.PointPlanet;
import gameobjects.SpaceShip;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Class 
 * Controller for the whole 2D-Game
 * @author Naomi Brügger
 *
 */
public class GameController extends PApplet {
	private int gameScreen;
	private PImage bg;
	private PFont title;
	private SpaceShip playerShip;
	private SpaceShip enemyShip;
	private List<SpaceShip> enemys;
	private List<FunkyPlanet> planets;
	private Timer time;
	private Random randomPos;

	public static void main(String[] args) {
		PApplet.main("controller.GameController");
	}

	/**
	 * Dimensions of the game and the background image
	 */
	public void settings() {
		size(450, 800);
		randomPos = new Random();
		bg = loadImage("staaars.jpg");
	}

	/**
	 * Here instances of the SpaceShips and the Timer are created 
	 */
	public void gameSetup() {
		//One PlayerShip-Object
		PVector posShip = new PVector(250, 750);
		playerShip = new PlayerShip(posShip, 60, 30, 5, 0xFF2B7080, 5);
		//Planets in ArrayList
		planets = new ArrayList<>();
		//EnemyShips in ArrayList
		enemys = new ArrayList<>();
		//Timer
		time = new Timer();
		time.scheduleAtFixedRate(spawnEnemy, 0, 2000); //for the enemys
		time.scheduleAtFixedRate(moveEnemy, 0, 200); //for the enemys movement
		time.scheduleAtFixedRate(spawnPlanet, 2000, 4000); //for the planets, starts with a delay
	}
	

	/**
	 * Timer for the spawning of the enemy
	 */
	private TimerTask spawnEnemy = new TimerTask() {
		@Override
		public void run() {
			enemyShip = new EnemyShip(new PVector(randomPos.nextInt(450), 0), 60, 30, 0xFFCC1830);
			enemys.add(enemyShip);
			
			enemys.forEach(e -> {
				if(e.getPosition().y > 700) {
					e.getPosition().y = -10;
				}
			});
		}
	};

	/**
	 * Timer for the movement of the enemy, in a specific interval
	 */
	private TimerTask moveEnemy = new TimerTask() {
		@Override
		public void run() {
			enemys.forEach(enemy -> {
				enemy.getPosition().y += 5;
			});
		}
	};
	
	/**
	 * Timer for the spawning of the planets
	 */
	private TimerTask spawnPlanet = new TimerTask() {
		@Override
		public void run() {
			for(int i = 0; i < 1; i++) {
				FunkyPlanet p = new PointPlanet(new PVector(randomPos.nextInt(400), randomPos.nextInt(500)), 30, 30, 1);
				planets.add(p);
			}
		}
	};
	
	/**
	 * distinguishes between three stages of the gameScreen-variable
	 */
	public void draw() {
		if (gameScreen == 0) {
			initScreen();
		} 
		else if (gameScreen == 1) {
			gameScreen();
		}
		else if (gameScreen == 2) {
			gameOverScreen();
		}
	}
	
	/**
	 * sets the gameScreen to 2, calls on the method gameSetup
	 */
	public void startGame() {
		gameSetup();
		gameScreen = 1;
	}
	
	/**
	 * sets the gameScreen to 2 and ends the TimerTasks
	 */
	public void endGame() {
		gameScreen = 2;
		spawnEnemy.cancel();
		moveEnemy.cancel();
		spawnPlanet.cancel();
	}
	
	/**
	 * switch to the game-screen with a mouse-click
	 */
	public void mousePressed() {
		if (gameScreen == 0) {
			startGame();
		}
	}

	/**
	 * Start-Screen
	 */
	public void initScreen() {
		background(bg);
		title = createFont("barcade3mild.ttf", 34);
		fill(0xFFCC21B2);
		textAlign(CENTER);
		textFont(title);
		text("Start your journey", 225, 400);
	}

	/**
	 * Game-Screen
	 */
	public void gameScreen() {
		background(bg);
		title = createFont("Zector.otf", 23);
		fill(0xFFCC21B2);
		textAlign(CENTER);
		textFont(title);
		text("Your score: " + ((PlayerShip) playerShip).getPlayerScore(), 120, 780);
		text("Your lifes: " + ((PlayerShip) playerShip).getPlayerHealth(), 350, 780);
		playerShip.drawShip(this);
		for(int i = 0; i < planets.size(); i++) {
			FunkyPlanet p = planets.get(i);
			p.drawPlanet(this);
		}
		
		for(int i = 0; i < enemys.size(); i++) {
			SpaceShip s = enemys.get(i);
			s.drawShip(this);
		}
		
		
		handleCollision();
	}
	
	/**
	 * Game Over-Screen
	 */
	public void gameOverScreen() {
		background(0x000000);
		title = createFont("barcade3mild.ttf", 40);
		fill(0xFFCC21B2);
		textAlign(CENTER);
		textFont(title);
		text("Game Over", 225, 400);
		title = createFont("Zector.otf", 23);
		textFont(title);
		text("Your score: " + ((PlayerShip) playerShip).getPlayerScore(), 225, 450);
			if(((PlayerShip) playerShip).getPlayerScore() == 10) {
				text("YOU WON!", 225, 500);
			} else if (((PlayerShip) playerShip).getPlayerHealth() == 0) {
				text("YOU DIED!", 225, 500);
			}
	}
	
	/**
	 * movement of the player with arrows
	 */
	public void keyPressed() {
		if (keyCode == RIGHT) {
			((PlayerShip) playerShip).movementRight(this);
		} else if (keyCode == LEFT) {
			((PlayerShip) playerShip).movementLeft();
		} else if(keyCode == UP) {
			((PlayerShip) playerShip).movementUp();
		} else if(keyCode == DOWN) {
			((PlayerShip) playerShip).movementDown(this);
		}
	}

	
	/**
	 * Takes care of what happens when there is a collision between objects (enemy/player, player/planet)
	 */
	public void handleCollision() {
		for(int i = 0; i < enemys.size(); i++) {
			EnemyShip e = (EnemyShip) enemys.get(i);
			if(checkShipCollision(playerShip, e)) { 
				((PlayerShip) playerShip).downHealth();
				enemys.remove(e); //enemy vanishes after collision
		}
			else if(((PlayerShip) playerShip).getPlayerHealth() == 0) {
				endGame(); //if health of player reaches 0, game over it is
			}
	}
		for(int i = 0; i < planets.size(); i++) {
			FunkyPlanet p = planets.get(i);
			if(checkPlanetCollision(playerShip, p)) {
				((PlayerShip) playerShip).upScore();
				planets.remove(p);
			} 
			
			if(((PlayerShip) playerShip).getPlayerScore() == 10) {
				endGame(); //if player manages to pick up ten planets, game won
			}
		}
	}
	
	/**
	 * Checks if there is a collision between the enemy and the player, 
	 * when the distance between the two is less then 45
	 * @param playerShip
	 * @param enemy
	 * @return
	 */
	private boolean checkShipCollision(SpaceShip playerShip, SpaceShip enemy) {
		float distance = playerShip.getPosition().dist(enemy.getPosition());
		return distance < 45;
	}
	
	/**
	 * Checks if there is a collision between the player and a planet
	 * when the distance between the two is less then 30
	 * @param playerShip
	 * @param p
	 * @return
	 */
	private boolean checkPlanetCollision(SpaceShip playerShip, FunkyPlanet p) {
		float distance = playerShip.getPosition().dist(p.getPosition());
		return distance < 30;
	}
	
	/**
	 * gives back the width of the display (for mock-up test)
	 * @return
	 */
	public int getDisplayWidth() {
		return width;
	}

}
