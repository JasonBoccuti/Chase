package edu.moravian.Entity;
import edu.moravian.Geometry.Point2D;


public abstract class Entity {
	
	public abstract void update(int delta);
	
	public abstract Point2D getLocation();
	
	public abstract void setState(String state);
	
	public abstract String getState();
	
}
