package edu.moravian.Game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import edu.moravian.Geometry.Point2D;

public class GameMap {
	
	private int screenWidth, screenHeight;
	private int tx, ty;
	private int plotX, plotY;
	private int sx, sy;
	private int dsx, dsy;
	private final int tileWidth = 32;
	private final int tileHeight = 32;
	
	private TiledMap map;
	
	public GameMap(int width, int height) throws SlickException {
		map = new TiledMap("res/map.tmx");
		screenWidth = width;
		screenHeight = height;
	}
	
	public void render(Point2D lowerLeft) {
		
		// Distance from the Upper Left Corner to the X and Y axis of the tile it is currently in
		tx = (int)(lowerLeft.getX() % tileWidth);
		ty = (int)((lowerLeft.getY() + screenHeight) % tileHeight);
		
		// How far in the negative direction (up and to the left) from the corner point to reach the tile's axes
		plotX = -1 * tx;
		plotY = -1 * ty;
		
		// Which tile do we want to start rendering at
		sx = (int) (lowerLeft.getX()/tileWidth);
		sy = (int) ((lowerLeft.getY() + screenHeight)/tileHeight);
		
		// How many tiles we need to render from the starting tile to cover at least the whole screen width/height
		dsx = (screenWidth/tileWidth) + 2;
		dsy = (screenHeight/tileHeight) + 2;
		
		map.render(plotX, plotY, sx, sy, dsx, dsy);
	}
	
	public double getWorldWidth() {
		return map.getWidth()*tileWidth;
	}
	
	public double getWorldHeight() {
		return map.getHeight()*tileHeight;
	}
}
