package edu.moravian.Entity;
import edu.moravian.Geometry.Point2D;


public class Prize extends Entity {
	
	private int screenWidth, screenHeight;
	private int prizeX, prizeY;
	private String currentState;

	public Prize(int scrWidth, int scrHeight) {
		screenWidth = scrWidth;
		screenHeight = scrHeight;
		prizeX = (int)(Math.random() * screenWidth);
		prizeY = (int)(Math.random() * screenHeight);
	}
	
	@Override
	public void update(int delta) {
		if (this.getState() == "found") {
			prizeX = (int)(Math.random() * screenWidth);
			prizeY = (int)(Math.random() * screenHeight);
		}
		else {
			// do nothing
		}
	}

	@Override
	public Point2D getLocation() {
		Point2D location = new Point2D(prizeX, prizeY);
		return location;
	}

	@Override
	public void setState(String state) {
		currentState = state;
	}

	@Override
	public String getState() {
		return currentState;
	}

}
