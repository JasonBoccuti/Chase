 package edu.moravian.Game;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import edu.moravian.Coordinate.ColemanCoordinateTranslator;
import edu.moravian.Coordinate.CoordinateTranslator;
import edu.moravian.Entity.Agent;
import edu.moravian.Entity.Entity;
import edu.moravian.Entity.Player;
import edu.moravian.Entity.Prize;
import edu.moravian.Geometry.Point2D;
import edu.moravian.Graphics.SpriteRenderer;

public class Game extends BasicGame 
{
	private CoordinateTranslator coordTran;
	private ColemanCoordinateTranslator cct;
	private Boolean exit, goUp, goDown, goLeft, goRight, playSound;
	private Point2D lowerLeftCorner;
	private Point2D playerWorldLoc;
	private Point playerScreenLoc;
	private Point2D agentWorldLoc;
	private Point2D prizeWorldLoc;
	private GameMap map;
	private GameSoundManager gsm;
	private int screenWidth, screenHeight;
	private double tileMapWidth;
	private double tileMapHeight;
	private final double worldWidth = 1920; // meters
	private final double worldHeight = 1440; // meters
	private final double velocity = 1.38582; // meters/second (Average walking pace of a Human Being)
	
	
	// Entity
	private ArrayList<Entity> ents;
	private Player player;
	private Agent agent;
	private Prize prize;
	
	private SpriteRenderer ren;
	
	
	public Game(int width, int height) throws SlickException {
		super("Game");
		this.screenWidth = width;
		this.screenHeight = height;
		exit = false;
		goUp = false;
		goDown = false;
		goLeft = false;
		goRight = false;
		playSound = false;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		map = new GameMap(screenWidth, screenHeight);
		tileMapWidth = map.getWorldWidth();
		tileMapHeight = map.getWorldHeight();
		playerScreenLoc = new Point(0, 0);
		lowerLeftCorner = new Point2D(0, 0);
		playerWorldLoc = new Point2D(800, 800);
		agentWorldLoc = new Point2D(-1,-1);
		prizeWorldLoc = new Point2D(0, 0);
	    // List of all entities
	    ents = new ArrayList<Entity>();
	    ents.add(player = new Player(screenWidth, screenHeight));
	    ents.add(agent = new Agent(screenWidth, screenHeight));
	    ents.add(prize = new Prize(screenWidth, screenHeight));
	    ren = new SpriteRenderer();
	    
	    prize.setState("init");
	    
	    gsm = new GameSoundManager();
	    gsm.playNextSong();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(lowerLeftCorner);
		
		for (Entity e : ents) {
			ren.render(e);
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {	
		if (gsm.isPlaying()) {
			// do nothing
		}
		else {
			gsm.playNextSong();
		}
		
		// Player movement / input 
		if(exit) {
			gc.exit();
		}
		if(playSound) {
			gsm.pauseSong();
			gsm.playSound();
			gsm.resumeSong();
		}
		if(goLeft) {
			double temp = playerWorldLoc.getX();
			playerWorldLoc.setX(temp -= (velocity*delta));
			if (playerWorldLoc.getX() < 0) {
				playerWorldLoc.setX(0);
			}
			player.setState("left");
			agent.setState("left");
			
		}
		else if(goRight) { 
			double temp = playerWorldLoc.getX();
			playerWorldLoc.setX(temp += (velocity*delta));
			if (playerWorldLoc.getX() > worldWidth) {
				playerWorldLoc.setX(worldWidth);
			}
			System.out.println("Velocity: " + velocity + ", delta: " + delta + ", playerX: " + playerWorldLoc.getX());
			player.setState("right");
			agent.setState("right");
		}
		else if(goUp) {
			double temp = playerWorldLoc.getY();
			playerWorldLoc.setY(temp += (velocity*delta));
			if (playerWorldLoc.getY() > worldHeight) {
				playerWorldLoc.setY(worldHeight);
			}
			player.setState("up");
			agent.setState("up");
		}
		else if(goDown) {
			double temp = playerWorldLoc.getY();
			playerWorldLoc.setY(temp -= (velocity*delta));
			if (playerWorldLoc.getY() < 0) {
				playerWorldLoc.setY(0);
			}
			player.setState("down");
			agent.setState("down");
		}
		else {
			player.setState("still");
		}
		
		lowerLeftCorner.setX(playerWorldLoc.getX() - (screenWidth/2));
		lowerLeftCorner.setY(playerWorldLoc.getY() - (screenHeight/2));
		
		
		//coordTran = new CoordinateTranslator(worldWidth, worldHeight, lowerLeftCorner.getX(), lowerLeftCorner.getY(), screenWidth, screenHeight);
		cct = new ColemanCoordinateTranslator(worldWidth, worldHeight, lowerLeftCorner, screenWidth, screenHeight);
		
//		playerScreenLoc = coordTran.worldToScreen(playerWorldLoc);
		playerScreenLoc = cct.worldToScreen(playerWorldLoc);
		
		System.out.println("playerWorldX: " + playerWorldLoc.getX());
		System.out.println("playerWorldY: " + playerWorldLoc.getY());
		System.out.println("lowerLeftX: " + lowerLeftCorner.getX());
		System.out.println("lowerLeftY: " + lowerLeftCorner.getY());
		System.out.println("playerScreenX: " + playerScreenLoc.getX());
		System.out.println("playerScreenY: " + playerScreenLoc.getY());
		
		player.giveNewPosition(playerScreenLoc);
		agent.givePlayerPosition(playerScreenLoc);
		
		for (Entity e : ents) {
			e.update(delta);
		}
		
	}
	
	@Override
    public void keyPressed(int key, char c) {
		if(c == 'q' || c == 'Q') {
			exit = true;
		}
		/*if(key == Input.KEY_SPACE) {
			playSound = true;
		}*/
		if(c == 'g' || c == 'G') {
			System.out.println("Player World X: " + playerWorldLoc.getX() + ", Player World Y: " + playerWorldLoc.getY());
			System.out.println("Player Screen X: " + playerScreenLoc.getX() + ", Player Screen Y: " + playerScreenLoc.getY());
		}
        if(c == 'w' || c == 'W' || key == Input.KEY_UP) {
            goUp = true;
        }
        if(c == 'a' || c == 'A' || key == Input.KEY_LEFT) {
            goLeft = true;
        }
        if(c == 's' || c == 'S' || key == Input.KEY_DOWN) {
            goDown = true;
        }
        if(c == 'd' || c == 'D' || key == Input.KEY_RIGHT) {
            goRight = true;
        }
	}
	@Override
    public void keyReleased(int key, char c) {
		if(c == 'q' || c == 'Q') {
			exit = false;
		}
		/*if(key == Input.KEY_SPACE) {
			playSound = false;
		}*/
        if(c == 'w' || c == 'W' || key == Input.KEY_UP) {
            goUp = false;
        }
        if(c == 'a' || c == 'A' || key == Input.KEY_LEFT) {
            goLeft = false;
        }
        if(c == 's' || c == 'S' || key == Input.KEY_DOWN) {
            goDown = false;
        }
        if(c == 'd' || c == 'D' || key == Input.KEY_RIGHT) {
            goRight = false;
        }
	}
}