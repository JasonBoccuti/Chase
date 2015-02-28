package edu.moravian.Test;
import org.junit.Test;

import edu.moravian.Geometry.Point2D;
import edu.moravian.Geometry.Vector2D;
import static org.junit.Assert.*;

/**
 *
 * @author mebjc01
 */
public class Point2DTest
{
    public final static double TOL = Point2D.TOL;

    @Test
    public void testCreation()
    {
        Point2D p = new Point2D();

        assertEquals(0.0, p.getX(), TOL);
        assertEquals(0.0, p.getY(), TOL);

        p = new Point2D(15.5, -32.7);
        assertEquals(15.5, p.getX(), TOL);
        assertEquals(-32.7, p.getY(), TOL);

        Point2D q = new Point2D(p);
        assertEquals(15.5, q.getX(), TOL);
        assertEquals(-32.7, q.getY(), TOL);
    }

    @Test
    public void testMinus()
    {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(10, -5);

        assertEquals(new Vector2D(-10, 5), p1.minus(p2));

        assertEquals(new Vector2D(10, -5), p2.minus(p1));

        Point2D p3 = new Point2D(-1, 13.7);

        assertEquals(new Vector2D(-11, 18.7), p3.minus(p2));
    }

    @Test
    public void testPlus()
    {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(-5, 13.42);
        Vector2D v = new Vector2D(1, 1);
        assertEquals(new Point2D(1, 1), p1.plus(v));

        assertEquals(new Point2D(-4, 14.42), p2.plus(v));
    }

    @Test
    public void testPlusEquals()
    {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(-5, 13.42);
        Vector2D v = new Vector2D(1, 1);
        p1.plusEquals(v);
        assertEquals(new Point2D(1, 1), p1);

        p2.plusEquals(v);
        assertEquals(new Point2D(-4, 14.42), p2);
    }

    @Test
    public void testScalePlusEquals()
    {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(-5, 13.42);
        Vector2D v = new Vector2D(1, 1);
        p1.scalePlusEquals(1.0, v);
        assertEquals(new Point2D(1, 1), p1);

        p2.scalePlusEquals(5.6, v);
        assertEquals(new Point2D(-5 + 5.6 * 1, 13.42 + 5.6 * 1), p2);

        Point2D p3 = new Point2D(p2);

        p3.scalePlusEquals(0, v);
        assertEquals(p2, p3);
    }

    @Test
    public void testScalePlus()
    {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(-5, 13.42);
        Vector2D v = new Vector2D(1, 1);
        Point2D result = p1.scalePlus(1.0, v);
        assertEquals(new Point2D(1, 1), result);

        result = p2.scalePlus(5.6, v);
        assertEquals(new Point2D(-5 + 5.6 * 1, 13.42 + 5.6 * 1), result);

        Point2D p3 = new Point2D(p2);

        result = p3.scalePlus(0, v);
        assertEquals(p2, result);
    }
}