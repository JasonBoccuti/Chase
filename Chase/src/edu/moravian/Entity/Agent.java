package edu.moravian.Entity;
import java.awt.Point;

import edu.moravian.Geometry.Point2D;
import edu.moravian.Geometry.Vector2D;


public class Agent extends Entity {
	
	@SuppressWarnings("unused")
	private Vector2D toPlayer;
	private int screenWidth, screenHeight;
	private int agentX, agentY;
	private String currentState;
	private Point playerPosition = new Point();
	
	public Agent(int scrWidth, int scrHeight) {
		screenWidth = scrWidth;
		screenHeight = scrHeight;	
		agentX = -1;
		agentY = screenHeight - 1;
	}
	
	@Override
	public void update(int delta) {
		agentX = playerPosition.x - 50;
		agentY = playerPosition.y + 50;
	}
	
	public void givePlayerPosition(Point playerPos) {
		playerPosition = playerPos;
		toPlayer = new Vector2D(playerPosition.x, playerPosition.y);
	}

	@Override
	public Point2D getLocation() {
		Point2D location = new Point2D(agentX, agentY);
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
