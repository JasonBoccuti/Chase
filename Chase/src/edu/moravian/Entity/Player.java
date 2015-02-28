package edu.moravian.Entity;
import java.awt.Point;

import edu.moravian.Geometry.Point2D;


public class Player extends Entity {
	
	@SuppressWarnings("unused")
	private int screenWidth, screenHeight;
	private int playerX, playerY;
	private String currentState;
	private Point changePlayerTo = new Point();

	public Player(int scrWidth, int scrHeight) {
		screenWidth = scrWidth;
		screenHeight = scrHeight;
		playerX = 0;
		playerY = screenHeight;
	}
	
	@Override
	public void update(int delta) {
		playerX = changePlayerTo.x;
		playerY = changePlayerTo.y;
	}
	
	public void giveNewPosition(Point newPosition) {
		changePlayerTo = newPosition;
	}
	
	public Point2D getLocation() {
		Point2D location = new Point2D(playerX, playerY);
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
