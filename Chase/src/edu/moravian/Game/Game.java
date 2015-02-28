 package edu.moravian.Game;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import edu.moravain.Graphics.SpriteRenderer;
import edu.moravian.Coordinate.CoordinateTranslator;
import edu.moravian.Entity.Agent;
import edu.moravian.Entity.Entity;
import edu.moravian.Entity.Player;
import edu.moravian.Entity.Prize;
import edu.moravian.Geometry.Point2D;

public class Game extends BasicGame 
{
	private CoordinateTranslator coordTran;
	private Boolean exit, goUp, goDown, goLeft, goRight, playSound;
	private Point2D lowerLeftCorner;
	private Point2D playerWorldLoc;
	private Point2D agentWorldLoc;
	private Point2D prizeWorldLoc;
	private GameMap map;
	private GameSoundManager gsm;
	private int screenWidth, screenHeight;
	private final double worldWidth = 1000; // meters
	private final double worldHeight = 1000; // meters
	private final double velocity = 1.38582; // meters/second (average walking pace of a human)
	
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
		lowerLeftCorner = new Point2D(0, 0);
		playerWorldLoc = new Point2D(0, 0);
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
	    
	    // Create a Coordinate Translator starting with wllx/wlly being in the bottom left of the world
	    coordTran = new CoordinateTranslator(worldWidth, worldHeight, 0, 0, screenWidth, screenHeight);
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
			playerWorldLoc.setX(temp -= (velocity*delta)/2);
			player.setState("left");
			agent.setState("left");
			
		}
		else if(goRight) { 
			double temp = playerWorldLoc.getX();
			playerWorldLoc.setX(temp += (velocity*delta)/2);
			player.setState("right");
			agent.setState("right");
		}
		else if(goUp) {
			double temp = playerWorldLoc.getY();
			playerWorldLoc.setY(temp += (velocity*delta)/2);
			player.setState("up");
			agent.setState("up");
		}
		else if(goDown) {
			double temp = playerWorldLoc.getY();
			playerWorldLoc.setY(temp -= (velocity*delta)/2);
			player.setState("down");
			agent.setState("down");
		}
		else {
			player.setState("still");
		}
		
		coordTran.setViewedWllx(playerWorldLoc.getX() - (screenWidth/2));
		coordTran.setViewedWlly(playerWorldLoc.getY() - (screenHeight/2));
		Point playerScreenLoc = new Point();
		playerScreenLoc = coordTran.worldToScreen(playerWorldLoc);
		
		player.giveNewPosition(playerScreenLoc);
		agent.givePlayerPosition(playerScreenLoc);
		
		for (Entity e : ents) {
			e.update(delta);
		}
		
		lowerLeftCorner.setX(playerWorldLoc.getX() - screenWidth/2);
		lowerLeftCorner.setY(playerWorldLoc.getY() - screenHeight/2);
		
	}
	
	@Override
    public void keyPressed(int key, char c) {
		if(c == 'q' || c == 'Q') {
			exit = true;
		}
		/*if(key == Input.KEY_SPACE) {
			playSound = true;
		}*/
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