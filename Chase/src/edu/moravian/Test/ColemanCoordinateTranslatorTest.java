/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.moravian.Test;

import java.awt.Point;

import org.junit.Test;

import edu.moravian.Coordinate.CoordinateTranslator;
import edu.moravian.Geometry.Point2D;
import static org.junit.Assert.*;

/**
 *
 * @author mebjc01
 */
public class ColemanCoordinateTranslatorTest {

    private Point screenPoints[];
    private Point2D worldPoints[];
    private CoordinateTranslator world;

    public ColemanCoordinateTranslatorTest()
    {
        screenPoints = new Point[9];
        worldPoints = new Point2D[9];
    }

    private void makePoints(int screenWidth, int screenHeight,
            double worldWidth, double worldHeight,
            Point2D lowerLeft)
    {
        double llx = lowerLeft.getX();
        double lly = lowerLeft.getY();

        world = new CoordinateTranslator(worldWidth, worldHeight, lowerLeft.getX(), lowerLeft.getY(), screenWidth, screenHeight);

        // four corners
        screenPoints[0] = new Point(0, 0);
        screenPoints[1] = new Point(screenWidth, 0);
        screenPoints[2] = new Point(0, screenHeight);
        screenPoints[3] = new Point(screenWidth, screenHeight);

        worldPoints[0] = new Point2D(0 + llx, worldHeight + lly);
        worldPoints[1] = new Point2D(worldWidth + llx, worldHeight + lly);
        worldPoints[2] = new Point2D(0 +llx, 0 + lly);
        worldPoints[3] = new Point2D(worldWidth + llx, 0 + lly);

        // 1/4 and 3/4 across row 1/4 down from top
        screenPoints[4] = new Point((int)(.25 * screenWidth), (int)(.25 * screenHeight));
        screenPoints[5] = new Point((int)(.75 * screenWidth), (int)(.25 * screenHeight));

        worldPoints[4] = new Point2D(.25 * worldWidth + llx, .75 * worldHeight + lly);
        worldPoints[5] = new Point2D(.75 * worldWidth + llx, .75 * worldHeight + lly);

        // middle
        screenPoints[6] = new Point((int)(.5 * screenWidth), (int)(.5 * screenHeight));

        worldPoints[6] = new Point2D(.5 * worldWidth + llx, .5 * worldHeight + lly);

        // 1/4 and 3/4 across row 3/4 down from top
        screenPoints[7] = new Point((int)(.25 * screenWidth), (int)(.75 * screenHeight));
        screenPoints[8] = new Point((int)(.75 * screenWidth), (int)(.75 * screenHeight));

        worldPoints[7] = new Point2D(.25 * worldWidth + llx, .25 * worldHeight + lly);
        worldPoints[8] = new Point2D(.75 * worldWidth + llx, .25 * worldHeight + lly);
    }

    public void checkPoints()
    {
        for(int i = 0; i < 9; i++)
        {
            // Because points are made up from doubles, we have to be careful
            // comparing them.  assertEquals on doubles can take a 3rd parameter
            // that is a tolerance, but to do so, we have to break down the
            // point into their components.
            assertEquals(worldPoints[i].getX(), world.screenToWorld(screenPoints[i]).getX(), 0.000001);
            assertEquals(worldPoints[i].getY(), world.screenToWorld(screenPoints[i]).getY(), 0.000001);

            // Because world points are ints, we don't have to worry.
            assertEquals(screenPoints[i], world.worldToScreen(worldPoints[i]));
        }
    }

    @Test
    public void testOneToOne()
    {
        makePoints(800, 600, 800, 600, new Point2D(0, 0));
        checkPoints();
    }

    @Test
    public void testOneToOnePositiveOffset()
    {
        makePoints(800, 600, 800, 600, new Point2D(10, 25));
        checkPoints();
    }

    @Test
    public void testOneToOneNegativeOffset()
    {
        makePoints(800, 600, 800, 600, new Point2D(-100, -5));
        checkPoints();
    }

    @Test
    public void testSmallerWorld()
    {
        makePoints(800, 600, 300, 150, new Point2D(0, 0));
        checkPoints();

        makePoints(800, 600, 300, 150, new Point2D(15, 10000));
        checkPoints();

        makePoints(800, 600, 300, 150, new Point2D(-1234, -1234.1));
        checkPoints();
    }

    @Test
    public void testDinkyWorld()
    {
        makePoints(1024, 768, 1.0, 768.0/1024.0, new Point2D(-1230.51234,1234.5412));
        checkPoints();
    }

    @Test
    public void testHugeWorld()
    {
        // This test actually fails for me if you scale by 12345.  It is
        // a floating-point round-off issue.
        makePoints(320, 200, 320*1345, 200*1345, new Point2D(1, -5));
        checkPoints();
    }
}