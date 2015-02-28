package edu.moravian.Coordinate;
import java.awt.Point;

import edu.moravian.Geometry.Point2D;

public class CoordinateTranslator {
	
	private double worldHeight = 0;
	private double worldWidth = 0;
	private int screenHeight = 0;
	private int screenWidth = 0;
	private double viewedWllx = 0;
	private double viewedWlly = 0;
	
	public CoordinateTranslator(double worldWidth, double worldHeight, double viewedWllx, double viewedWlly, int screenWidth, int screenHeight) {
		this.worldHeight = worldHeight;
		this.worldWidth = worldWidth;
		this.viewedWllx = viewedWllx;
		this.viewedWlly = viewedWlly;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;	
	}
	
	public void setViewedWllx(double x) {
		viewedWllx = x;
	}
	
	public void setViewedWlly(double y) {
		viewedWlly = y;
	}
	
	public Point2D screenToWorld(int sx, int sy) {
		Point2D worldPoint = new Point2D();
		
		worldPoint.setX(((worldWidth/screenWidth)*sx) + viewedWllx);
		worldPoint.setY(-((worldHeight*sy)/screenHeight) - viewedWlly + worldHeight);
		
		
		return worldPoint;
	}
	
	public Point2D screenToWorld(Point screenPoint ) {
		Point2D worldPoint = new Point2D();
		
		worldPoint.setX(((worldWidth*screenPoint.getX())/screenWidth) + viewedWllx);
		worldPoint.setY(-((worldHeight*screenPoint.getY())/screenHeight) - viewedWlly + worldHeight);
		
		return worldPoint;
	}
	
	public Point worldToScreen(double wx, double wy) {
		Point screenPoint = new Point();
		
		int sx = (int) ((wx - viewedWllx) * (screenWidth/worldWidth)); 
		int sy = (int) ((worldHeight - wy - viewedWlly) * (screenHeight/worldHeight));
		
		screenPoint.setLocation(sx, sy);
		
		return screenPoint;
	}
	
	public Point worldToScreen(Point2D worldPoint) {
		Point screenPoint  = new Point();
		
		int sx = (int) ((worldPoint.getX() - viewedWllx) * (screenWidth/worldWidth)); 
		int sy = (int) ((worldHeight - worldPoint.getY() - viewedWlly) * (screenHeight/worldHeight));
		
		screenPoint.setLocation(sx, sy);
		
		return screenPoint;
	}
}
