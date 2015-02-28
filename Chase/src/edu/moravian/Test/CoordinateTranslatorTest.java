package edu.moravian.Test;


import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import edu.moravian.Coordinate.CoordinateTranslator;
import edu.moravian.Geometry.Point2D;


public class CoordinateTranslatorTest {

	private double worldHeight;
	private double worldWidth;
	private int screenHeight;
	private int screenWidth;
	private double viewedWllx;
	private double viewedWlly;
	private Point2D worldPoint = new Point2D();
	private Point screenPoint = new Point();
	private double TOL = 0.00000001;
	
	@Test
	public void screenToWorldCoordinateTestWithOneToOne() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint = coordTran.screenToWorld((int)(j*screenWidth), (int)(i*screenHeight));
				assertEquals(j*screenWidth, worldPoint.getX(), TOL);
				assertEquals(screenHeight - (i*screenHeight), worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void screenToWorldCoordinateTestWithOneToOneWithOffset() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 100;
		viewedWlly = 200;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint = coordTran.screenToWorld((int)(j*screenWidth), (int)(i*screenHeight));
				assertEquals(j*screenWidth + viewedWllx, worldPoint.getX(), TOL);
				assertEquals(screenHeight - ((i*screenHeight) + viewedWlly), worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void screenToWorldCoordinateTestWithProportional() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint = coordTran.screenToWorld((int)(j*screenWidth), (int)(i*screenHeight));
				assertEquals((j*screenWidth)*.1, worldPoint.getX(), TOL);
				assertEquals((screenHeight - (i*screenHeight))*.1, worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void screenToWorldCoordinateTestWithProportionalWithOffset() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = -40;
		viewedWlly = -30;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint = coordTran.screenToWorld((int)(j*screenWidth), (int)(i*screenHeight));
				assertEquals((j*screenWidth)*.1 + viewedWllx, worldPoint.getX(), TOL);
				assertEquals(screenHeight*.1 - ((i*screenHeight)*.1 + viewedWlly), worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void screenToWorldPointTestWithOneToOne() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint.setLocation((int)(j*screenWidth), (int)(i*screenHeight));
				worldPoint = coordTran.screenToWorld(screenPoint);
				assertEquals(j*screenWidth, worldPoint.getX(), TOL);
				assertEquals(screenHeight - (i*screenHeight), worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void screenToWorldPointTestWithOneToOneWithOffset() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 100;
		viewedWlly = 200;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint.setLocation((int)(j*screenWidth), (int)(i*screenHeight));
				worldPoint = coordTran.screenToWorld(screenPoint);
				assertEquals(j*screenWidth + viewedWllx, worldPoint.getX(), TOL);
				assertEquals(screenHeight - ((i*screenHeight) + viewedWlly), worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void screenToWorldPointTestWithProportional() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint.setLocation((int)(j*screenWidth), (int)(i*screenHeight));
				worldPoint = coordTran.screenToWorld(screenPoint);
				assertEquals((j*screenWidth)*.1, worldPoint.getX(), TOL);
				assertEquals((screenHeight - (i*screenHeight))*.1, worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void screenToWorldPointTestWithProportionalWithOffset() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = -40;
		viewedWlly = -30;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint.setLocation((int)(j*screenWidth), (int)(i*screenHeight));
				worldPoint = coordTran.screenToWorld(screenPoint);
				assertEquals((j*screenWidth)*.1 + viewedWllx, worldPoint.getX(), TOL);
				assertEquals(screenHeight*.1 - ((i*screenHeight)*.1 + viewedWlly), worldPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void worldToScreenCoordinateTestWithOneToOne() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint = coordTran.worldToScreen((int)(j*worldWidth), (int)(i*worldHeight));
				assertEquals(j*worldWidth, screenPoint.getX(), TOL);
				assertEquals(worldHeight - (i*worldHeight), screenPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void worldToScreenCoordinateTestWithOneToOneWithOffset() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 100;
		viewedWlly = 200;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint = coordTran.worldToScreen((int)(j*worldWidth), (int)(i*worldHeight));
				assertEquals(j*worldWidth - viewedWllx, screenPoint.getX(), TOL);
				assertEquals(worldHeight - ((i*worldHeight) + viewedWlly), screenPoint.getY(), TOL);
				
			}
		}
		
	}
	
	@Test
	public void worldToScreenCoordinateTestWithProportional() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint = coordTran.worldToScreen((int)(j*worldWidth), (int)(i*worldHeight));
				assertEquals((j*worldWidth)*10, screenPoint.getX(), TOL);
				assertEquals((worldHeight - (i*worldHeight))*10, screenPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void worldToScreenCoordinateTestWithProportionalWithOffset() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = -40;
		viewedWlly = -30;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				screenPoint = coordTran.worldToScreen((int)(j*worldWidth), (int)(i*worldHeight));
				assertEquals((j*worldWidth)*10 - viewedWllx*10, screenPoint.getX(), TOL);
				assertEquals(worldHeight*10 - ((i*worldHeight)*10 + viewedWlly*10), screenPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void worldToScreenPointTestWithOneToOne() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint.setX((int)(j*worldWidth));
				worldPoint.setY((int)(i*worldHeight));
				screenPoint = coordTran.worldToScreen(worldPoint);
				assertEquals(j*worldWidth, screenPoint.getX(), TOL);
				assertEquals(worldHeight - (i*worldHeight), screenPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void worldToScreenPointTestWithOneToOneWithOffset() {
		worldHeight = 600;
		worldWidth = 800;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 100;
		viewedWlly = 200;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint.setX((int)(j*worldWidth));
				worldPoint.setY((int)(i*worldHeight));
				screenPoint = coordTran.worldToScreen(worldPoint);
				assertEquals(j*worldWidth - viewedWllx, screenPoint.getX(), TOL);
				assertEquals(worldHeight - ((i*worldHeight) + viewedWlly), screenPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void worldToScreenPointTestWithProportional() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = 0;
		viewedWlly = 0;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint.setX((int)(j*worldWidth));
				worldPoint.setY((int)(i*worldHeight));
				screenPoint = coordTran.worldToScreen(worldPoint);
				assertEquals((j*worldWidth)*10, screenPoint.getX(), TOL);
				assertEquals((worldHeight - (i*worldHeight))*10, screenPoint.getY(), TOL);
				
			}
		}
	}
	
	@Test
	public void worldToScreenPointsTestWithProportionalWithOffset() {
		worldHeight = 60;
		worldWidth = 80;
		screenHeight = 600;
		screenWidth = 800;
		viewedWllx = -40;
		viewedWlly = -30;
		
		CoordinateTranslator coordTran = new CoordinateTranslator(worldWidth, worldHeight, viewedWllx, viewedWlly, screenWidth, screenHeight);		
		
		for(double i=0; i<=1; i+=0.25) {
			for(double j=0; j<=1; j+=0.25) {
				worldPoint.setX((int)(j*worldWidth));
				worldPoint.setY((int)(i*worldHeight));
				screenPoint = coordTran.worldToScreen(worldPoint);
				assertEquals((j*worldWidth)*10 - viewedWllx*10, screenPoint.getX(), TOL);
				assertEquals(worldHeight*10 - ((i*worldHeight)*10 + viewedWlly*10), screenPoint.getY(), TOL);
				
			}
		}
	}
}
