package edu.moravain.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import edu.moravian.Entity.Agent;
import edu.moravian.Entity.Entity;
import edu.moravian.Entity.Player;
import edu.moravian.Entity.Prize;


public class SpriteRenderer {
	
	private Image dragonBall = null;
	private Image spriteSheetImage = null;
	private SpriteSheet spriteSheet;
	private Animation gokuUp, gokuDown, gokuLeft, gokuRight, gokuStill, vegetaUp, vegetaDown, vegetaLeft, vegetaRight;
	private int spriteWidth;
	private int spriteHeight;
	private float spriteSheetWidth;
	private float spriteSheetHeight;
	private int spritesPerRow = 6;
	private int spritesPerColumn = 4;
	private int duration = 200; // Time to display each sprite
	private int spriteX;
	private int spriteY;
	
	public SpriteRenderer() throws SlickException {
		dragonBall = new Image("res/4StarDragonBall.png");
		spriteSheetImage = new Image("res/dbzcharacters.png");
		spriteSheetWidth = spriteSheetImage.getWidth();
	    spriteSheetHeight = spriteSheetImage.getHeight();
	    // Get width of sprite based on width of the sheet and how many sprites are in it
	    spriteWidth = (int)(spriteSheetWidth/spritesPerRow);
	    // Get height similarly
	    spriteHeight = (int)(spriteSheetHeight/spritesPerColumn);
	    // Now create a SpriteSheet object with the new SpriteSheet
	    spriteSheet = new SpriteSheet(spriteSheetImage, spriteWidth, spriteHeight);
	    // SpriteSheet, Start Column, Start Row, End Column, End Row, Scan Horizontally, How long per Image, Continually Cycle 
	    gokuUp      = new Animation(spriteSheet, 0, 0, 2, 0, true, duration, true);
	    gokuRight   = new Animation(spriteSheet, 0, 1, 2, 1, true, duration, true);
	    gokuDown    = new Animation(spriteSheet, 0, 2, 2, 2, true, duration, true);
	    gokuLeft    = new Animation(spriteSheet, 0, 3, 2, 3, true, duration, true);
	    gokuStill	= new Animation(spriteSheet, 1, 2, 1, 2, true, duration, true);
	    vegetaUp    = new Animation(spriteSheet, 3, 0, 5, 0, true, duration, true);
	    vegetaRight = new Animation(spriteSheet, 3, 1, 5, 1, true, duration, true);
	    vegetaDown  = new Animation(spriteSheet, 3, 2, 5, 2, true, duration, true);
	    vegetaLeft  = new Animation(spriteSheet, 3, 3, 5, 3, true, duration, true);
	}
	
	public void render(Entity e) {
		if (e instanceof Player) {
			renderPlayer((Player)e);
		}
		else if (e instanceof Agent) {
			renderAgent((Agent)e);
		}
		else if (e instanceof Prize) {
			renderPrize((Prize)e);
		}
		else {
			throw new RuntimeException("Could not render");
		}
		
	}
	
	private void renderPlayer(Player player) {
		spriteX = (int) player.getLocation().getX();
		spriteY = (int) player.getLocation().getY();
		
		if (player.getState() == "up") {
			gokuUp.draw(spriteX, spriteY);
		}
		if (player.getState() == "down") {
			gokuDown.draw(spriteX, spriteY);
		}
		if (player.getState() == "left") {
			gokuLeft.draw(spriteX, spriteY);
		}
		if (player.getState() == "right") {
			gokuRight.draw(spriteX, spriteY);
		}
		if (player.getState() == "still") {
			gokuStill.draw(spriteX, spriteY);
		}
	}
	
	private void renderAgent(Agent agent) {
		spriteX = (int) agent.getLocation().getX();
		spriteY = (int) agent.getLocation().getY();
		
		if (agent.getState() == "up") {
			vegetaUp.draw(spriteX, spriteY);
		}
		if (agent.getState() == "down") {
			vegetaDown.draw(spriteX, spriteY);
		}
		if (agent.getState() == "left") {
			vegetaLeft.draw(spriteX, spriteY);
		}
		if (agent.getState() == "right") {
			vegetaRight.draw(spriteX, spriteY);
		}
	}
	
	private void renderPrize(Prize prize) {
		spriteX = (int) prize.getLocation().getX();
		spriteY = (int) prize.getLocation().getY();
		
		dragonBall.draw(spriteX, spriteY);
		if (prize.getState() == "init") {
			dragonBall.draw(spriteX, spriteY);
		}
		if (prize.getState() == "found") {
			dragonBall.draw(spriteX, spriteY);
			prize.setState("missing");
		}
	}
}
