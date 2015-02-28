package edu.moravian.Test;

import org.junit.Test;

import edu.moravian.Geometry.Vector2D;
import static org.junit.Assert.*;

/**
 *
 * @author mebjc01
 */
public class Vector2DTest
{
    public static double TOL = Vector2D.TOL;

    public static final double sinPI6 = Math.sin(Math.PI / 6.0);
    public static final double cosPI6 = Math.cos(Math.PI / 6.0);

    public static final double sinPI4 = Math.sin(Math.PI / 4.0);
    public static final double cosPI4 = Math.cos(Math.PI / 4.0);

    public static final double sinPI3 = Math.sin(Math.PI / 3.0);
    public static final double cosPI3 = Math.cos(Math.PI / 3.0);

    public static final double sinPI2 = Math.sin(Math.PI / 2.0);
    public static final double cosPI2 = Math.cos(Math.PI / 2.0);

    // Vectors to be used in the tests
    public static final Vector2D zero = new Vector2D(0, 0);

    // A collection of unit vectors in each quadrant.  Note that the
    // corresponding vectors in each quadrant are orthogonal or the
    // inverse
    public static final Vector2D q1unita = new Vector2D(cosPI2, sinPI2);
    public static final Vector2D q1unitb = new Vector2D(cosPI3, sinPI3);
    public static final Vector2D q1unitc = new Vector2D(cosPI4, sinPI4);
    public static final Vector2D q1unitd = new Vector2D(cosPI6, sinPI6);

    public static final Vector2D q2unita = new Vector2D(-sinPI2, cosPI2);
    public static final Vector2D q2unitb = new Vector2D(-sinPI3, cosPI3);
    public static final Vector2D q2unitc = new Vector2D(-sinPI4, cosPI4);
    public static final Vector2D q2unitd = new Vector2D(-sinPI6, cosPI6);

    public static final Vector2D q3unita = new Vector2D(-cosPI2, -sinPI2);
    public static final Vector2D q3unitb = new Vector2D(-cosPI3, -sinPI3);
    public static final Vector2D q3unitc = new Vector2D(-cosPI4, -sinPI4);
    public static final Vector2D q3unitd = new Vector2D(-cosPI6, -sinPI6);

    public static final Vector2D q4unita = new Vector2D(sinPI2, -cosPI2);
    public static final Vector2D q4unitb = new Vector2D(sinPI3, -cosPI3);
    public static final Vector2D q4unitc = new Vector2D(sinPI4, -cosPI4);
    public static final Vector2D q4unitd = new Vector2D(sinPI6, -cosPI6);

    // A collection of non-unit vectors.
    public static final Vector2D q1a = new Vector2D(1.0, 1.0);
    public static final Vector2D q1b = new Vector2D(cosPI2 * 12.7, sinPI2 * 12.7);
    public static final Vector2D q1c = new Vector2D(cosPI3 * 3.5, sinPI3 * 3.5);
    public static final Vector2D q1d = new Vector2D(cosPI4 * 1000.0, sinPI4 * 1000.0);
    public static final Vector2D q1e = new Vector2D(cosPI6 * .001, sinPI6 * .001);

    public static final Vector2D q2a = new Vector2D(-1.0, 1.0);
    public static final Vector2D q2b = new Vector2D(-sinPI2 * 12.7, cosPI2 * 12.7); // first coord is 0
    public static final Vector2D q2c = new Vector2D(-sinPI3 * 3.5, cosPI3 * 3.5);
    public static final Vector2D q2d = new Vector2D(-sinPI4 * 1000.0, cosPI4 * 1000.0);
    public static final Vector2D q2e = new Vector2D(-sinPI6 * .001, cosPI6 * .001);

    public static final Vector2D q3a = new Vector2D(-1.0, -1.0);
    public static final Vector2D q3b = new Vector2D(-cosPI2 * 12.7, -sinPI2 * 12.7);
    public static final Vector2D q3c = new Vector2D(-cosPI3 * 3.5, -sinPI3 * 3.5);
    public static final Vector2D q3d = new Vector2D(-cosPI4 * 1000.0, -sinPI4 * 1000.0);
    public static final Vector2D q3e = new Vector2D(-cosPI6 * .001, -sinPI6 * .001);

    public static final Vector2D q4a = new Vector2D(1.0, -1.0);
    public static final Vector2D q4b = new Vector2D(sinPI2 * 12.7, -cosPI2 * 12.7);
    public static final Vector2D q4c = new Vector2D(sinPI3 * 3.5, -cosPI3 * 3.5);
    public static final Vector2D q4d = new Vector2D(sinPI4 * 1000.0, -cosPI4 * 1000.0);
    public static final Vector2D q4e = new Vector2D(sinPI6 * .001, -cosPI6 * .001);

    // Test the TOL limits - All these vectors are actually the zero vector
    public static final Vector2D tolq1 = new Vector2D(TOL / 10.0, TOL / 10.0);
    public static final Vector2D tolq2 = new Vector2D(-1.0 * TOL / 10.0, TOL / 10.0);
    public static final Vector2D tolq3 = new Vector2D(-1.0 * TOL / 10.0, -1.0 * TOL / 10.0);
    public static final Vector2D tolq4 = new Vector2D(TOL / 10.0, -1.0 * TOL / 10.0);


    @Test
    public void testNewEmptyVector()
    {
        Vector2D v = new Vector2D();

        assertEquals(v.getX(), 0.0, TOL);
        assertEquals(v.getY(), 0.0, TOL);
    }

    @Test
    public void testNewDefinedVector()
    {
        Vector2D v = new Vector2D( -1.5, 1);

        assertEquals(v.getX(), -1.5, TOL);
        assertEquals(v.getY(), 1.0, TOL);
    }

    @Test
    public void testMagnitude()
    {
        assertEquals(zero.magnitude(), 0.0, TOL);

        assertEquals(q1unita.magnitude(), 1.0, TOL);
        assertEquals(q2unitb.magnitude(), 1.0, TOL);
        assertEquals(q3unitc.magnitude(), 1.0, TOL);
        assertEquals(q4unitd.magnitude(), 1.0, TOL);

        assertEquals(q1a.magnitude(), Math.sqrt(2), TOL);
        assertEquals(q2b.magnitude(), 12.7, TOL);
        assertEquals(q3c.magnitude(), 3.5, TOL);
        assertEquals(q4d.magnitude(), 1000.0, TOL);
        assertEquals(q1e.magnitude(), .001, TOL);

        assertEquals(tolq1.magnitude(), 0.0, TOL);
        assertEquals(tolq2.magnitude(), 0.0, TOL);
        assertEquals(tolq3.magnitude(), 0.0, TOL);
        assertEquals(tolq4.magnitude(), 0.0, TOL);
    }

    @Test
    public void testMagnitudeSq()
    {
        assertEquals(zero.magnitudeSq(), 0.0, TOL);

        assertEquals(q1unita.magnitudeSq(), 1.0, TOL);
        assertEquals(q2unitb.magnitudeSq(), 1.0, TOL);
        assertEquals(q3unitc.magnitudeSq(), 1.0, TOL);
        assertEquals(q4unitd.magnitudeSq(), 1.0, TOL);

        assertEquals(q1a.magnitudeSq(), 2.0, TOL);
        assertEquals(q2b.magnitudeSq(), 12.7 * 12.7, TOL);
        assertEquals(q3c.magnitudeSq(), 3.5 * 3.5, TOL);
        assertEquals(q4d.magnitudeSq(), 1000.0 * 1000.0, TOL);
        assertEquals(q1e.magnitudeSq(), .001 * .001, TOL);
    }

    @Test
    public void testAngle()
    {
        assertEquals(zero.angle(), 0.0, TOL);
        assertEquals(q1a.angle(), Math.PI / 4.0, TOL);
        assertEquals(q1b.angle(), Math.PI / 2.0, TOL);
        assertEquals(q1c.angle(), Math.PI / 3.0, TOL);
        assertEquals(q1d.angle(), Math.PI / 4.0, TOL);

        assertEquals(q2a.angle(), Math.PI / 4.0 + Math.PI / 2.0, TOL);
        assertEquals(q2b.angle(), Math.PI / 2.0 + Math.PI / 2.0, TOL);
        assertEquals(q2c.angle(), Math.PI / 3.0 + Math.PI / 2.0, TOL);
        assertEquals(q2d.angle(), Math.PI / 4.0 + Math.PI / 2.0, TOL);

        assertEquals(q3a.angle(), Math.PI / 4.0 - Math.PI, TOL);
        assertEquals(q3b.angle(), Math.PI / 2.0 - Math.PI, TOL);
        assertEquals(q3c.angle(), Math.PI / 3.0 - Math.PI, TOL);
        assertEquals(q3d.angle(), Math.PI / 4.0 - Math.PI, TOL);

        assertEquals(q4a.angle(), Math.PI / 4.0 - Math.PI / 2.0, TOL);
        assertEquals(q4b.angle(), Math.PI / 2.0 - Math.PI / 2.0, TOL);
        assertEquals(q4c.angle(), Math.PI / 3.0 - Math.PI / 2.0, TOL);
        assertEquals(q4d.angle(), Math.PI / 4.0 - Math.PI / 2.0, TOL);

        // Magnitude of zero vectors is 0
        assertEquals(tolq1.angle(), 0.0, TOL);
        assertEquals(tolq2.angle(), 0.0, TOL);
        assertEquals(tolq3.angle(), 0.0, TOL);
        assertEquals(tolq4.angle(), 0.0, TOL);
    }

    @Test
    public void testNormalize()
    {
        // Make sure the zero vector remains the zero vector
        Vector2D z = new Vector2D(zero);
        z.normalize();
        assertEquals(z.magnitude(), 0.0, TOL);

        // Check to make sure that small vectors become the zero vector
        // and do not normalize to 1
        Vector2D a = new Vector2D(tolq1);
        a.normalize();
        assertEquals(a.magnitude(), 0.0, TOL);

        a = new Vector2D(tolq2);
        a.normalize();
        assertEquals(a.magnitude(), 0.0, TOL);

        a = new Vector2D(tolq3);
        a.normalize();
        assertEquals(a.magnitude(), 0.0, TOL);

        a = new Vector2D(tolq4);
        a.normalize();
        assertEquals(a.magnitude(), 0.0, TOL);

        // Check some non-unit vectors
        a = new Vector2D(q1c);
        a.normalize();
        assertEquals(a.magnitude(), 1.0, TOL);

        a = new Vector2D(q2d);
        a.normalize();
        assertEquals(a.magnitude(), 1.0, TOL);

        a = new Vector2D(q3e);
        a.normalize();
        assertEquals(a.magnitude(), 1.0, TOL);

        // Make sure unit vectors remain unchanged
        a = new Vector2D(q1unita);
        a.normalize();
        assertEquals(a, q1unita);

        a = new Vector2D(q2unitb);
        a.normalize();
        assertEquals(a, q2unitb);

        a = new Vector2D(q3unitc);
        a.normalize();
        assertEquals(a, q3unitc);

        a = new Vector2D(q4unitd);
        a.normalize();
        assertEquals(a, q4unitd);
    }

    @Test
    public void testGetNormalized()
    {
         // Make sure the zero vector remains the zero vector
        Vector2D z = new Vector2D(zero);
        Vector2D result = z.getNormalized();
        assertEquals(result.magnitude(), 0.0, TOL);

        // Check to make sure that small vectors become the zero vector
        // and do not normalize to 1
        Vector2D a = new Vector2D(tolq1);
        result = a.getNormalized();
        assertEquals(result.magnitude(), 0.0, TOL);

        a = new Vector2D(tolq2);
        result = a.getNormalized();
        assertEquals(result.magnitude(), 0.0, TOL);

        a = new Vector2D(tolq3);
        result = a.getNormalized();
        assertEquals(result.magnitude(), 0.0, TOL);

        a = new Vector2D(tolq4);
        result = a.getNormalized();
        assertEquals(result.magnitude(), 0.0, TOL);

        // Check some non-unit vectors
        a = new Vector2D(q1c);
        result = a.getNormalized();
        assertEquals(result.magnitude(), 1.0, TOL);

        a = new Vector2D(q2d);
        result = a.getNormalized();
        assertEquals(result.magnitude(), 1.0, TOL);

        a = new Vector2D(q3e);
        result = a.getNormalized();
        assertEquals(result.magnitude(), 1.0, TOL);

        // Make sure unit vectors remain unchanged
        a = new Vector2D(q1unita);
        result = a.getNormalized();
        assertEquals(result, q1unita);

        a = new Vector2D(q2unitb);
        result = a.getNormalized();
        assertEquals(result, q2unitb);

        a = new Vector2D(q3unitc);
        result = a.getNormalized();
        assertEquals(result, q3unitc);

        a = new Vector2D(q4unitd);
        result = a.getNormalized();
        assertEquals(result, q4unitd);
    }

    @Test
    public void testPlusEqual()
    {
        // check a typical case first
        Vector2D a = new Vector2D( 2.5, 6.5);
        Vector2D b = new Vector2D( -5.6, 9.5);

        a.plusEquals(b);

        assertEquals(a, new Vector2D(2.5 - 5.6, 6.5 + 9.5));

        // Adding a vector below the tolerance shouldn't change the vector
        Vector2D aOrg = new Vector2D(a);
        a.plusEquals(tolq1);
        assertEquals(aOrg, a);
    }

    @Test
    public void testMinusEqual()
    {
        // check a typical case first
        Vector2D c = new Vector2D(2.5, 6.5);
        Vector2D d = new Vector2D(-5.6, 9.5);

        c.minusEquals(d);
        assertEquals(c, new Vector2D(2.5 + 5.6, 6.5 - 9.5));

        // If the the result is below tolerance, make sure the vector
        // goes to zero
        Vector2D a = new Vector2D(2.5, 6.5);
        // just a tiny bit bigger than a (Note the div 2 to make sure
        // floating point round-off doesn't screw up this test
        Vector2D b = new Vector2D(2.5 + TOL / 2.0, 6.5 + TOL / 2.0);
        b.minusEquals(a);
        assertEquals(b, zero);

        // Subtracting a vector below tolerance shouldn't change the vector
        Vector2D bOrg = new Vector2D(b);
        b.minusEquals(tolq3);

        assertEquals(bOrg, b);
    }

    @Test
    public void testTimesEqual()
    {
        // check a typical case first
        Vector2D a = new Vector2D(9.5, 6.7);
        a.timesEquals(13);
        assertEquals(a, new Vector2D(9.5 * 13, 6.7 * 13));

        // check that the vector goes to zero
        a.timesEquals(0);
        assertEquals(a, zero);

        // check the threshold
        Vector2D b = new Vector2D(1, 1);
        b.timesEquals(TOL / 10);
        assertEquals(b, zero);
    }

    @Test
    public void testDivEqual()
    {
        // division by zero will cause a runtime error, which is hard to catch

        // check a typical case first
        Vector2D a = new Vector2D(42.5, 15.23);
        a.divideEquals(19.2);
        assertEquals(a, new Vector2D(42.5 / 19.2, 15.23 / 19.2));

        // check the threshold
        Vector2D b = new Vector2D(1, 1);
        b.divideEquals(1/(TOL / 10));
        assertEquals(b, zero);
    }

    @Test
    public void testUnaryMinus()
    {
        Vector2D u = new Vector2D(q3c);

        u.negate();

        // A reversed vector should have the same magnitude
        assertEquals(u.magnitude(), q3c.magnitude(), TOL);

        // Because angle returns values between -PI and PI, if we
        // take the absolute value of the angles, they will be
        // complementary - i.e. sum to PI
        assertEquals(Math.abs(u.angle()) + Math.abs(q3c.angle()), Math.PI, TOL);
    }

    @Test
    public void testGetLeftOrtho()
    {
        // The dot product of a vector and either of its orthogonal
        // vectors is 0
        Vector2D a = q1b.getLeftOrtho();
        assertEquals(a.dot(q1b), 0.0, TOL);

        // The left ortho of the left ortho should be the reverse of the original
        Vector2D b = a.getLeftOrtho();
        b.negate();
        assertEquals(b, q1b);
        b.negate();

        // one more and you should have the rightOrtho of the original
        Vector2D c = b.getLeftOrtho();
        assertEquals(c, q1b.getRightOrtho());

        // and once more gives the original
        Vector2D d = c.getLeftOrtho();
        assertEquals(d, q1b);
    }

    @Test
    public void testSetLeftOrtho()
    {
        Vector2D v = new Vector2D(15.5, 42.9);
        Vector2D u = new Vector2D(v);

        // make u and v orthogonal with u the left ortho of v
        u.setLeftOrtho();

        // the dot product should be 0 and their magnitudes equal
        assertEquals(0, u.dot(v), TOL);
        assertEquals(u.magnitude(), v.magnitude(), TOL);

        // Now make u the inverse of v.
        u.setLeftOrtho();
        
        // The dot product should be negative
        assertTrue(u.dot(v) < 0);

        u.setLeftOrtho();

        // Three lefts make a right...
        assertEquals(u, v.getRightOrtho());
    }

    @Test
    public void testGetRightOrtho()
    {
        // The dot product of a vector and either of its orthogonal
        // vectors is 0
        Vector2D a = q1b.getRightOrtho();
        assertEquals(a.dot(q1b), 0.0, TOL);

        // The right ortho of the right ortho should be the reverse of the original
        Vector2D b = a.getRightOrtho();
        b.negate();
        assertEquals(b, q1b);
        b.negate();

        // one more and you should have the leftOrtho of the original
        Vector2D c = b.getRightOrtho();
        assertEquals(c, q1b.getLeftOrtho());

        // and once more gives the original
        Vector2D d = c.getRightOrtho();
        assertEquals(d, q1b);
    }

    @Test
    public void testSetRightOrtho()
    {
        Vector2D v = new Vector2D(15.5, 42.9);
        Vector2D u = new Vector2D(v);

        // make u and v orthogonal with u the right ortho of v
        u.setRightOrtho();

        // the dot product should be 0 and their magnitudes equal
        assertEquals(0, u.dot(v), TOL);
        assertEquals(u.magnitude(), v.magnitude(), TOL);

        // Now make u the inverse of v.
        u.setRightOrtho();

        // The dot product should be negative
        assertTrue(u.dot(v) < 0);

        u.setRightOrtho();

        // Three rights make a left
        assertEquals(u, v.getLeftOrtho());
    }

    @Test
    public void testPlus()
    {
        // check a typical case first
        Vector2D a = new Vector2D(2.5, 6.5);
        Vector2D b = new Vector2D(-5.6, 9.5);
        Vector2D c = a.plus(b);
        assertEquals(c, new Vector2D(2.5 - 5.6, 6.5 + 9.5));

        // Adding a vector below the tolerance should be the identity
        c = b.plus(tolq3);
        assertEquals(c, b);
    }

    @Test
    public void testMinus()
    {
        // check a typical case first
        Vector2D c = new Vector2D(2.5, 6.5);
        Vector2D d = new Vector2D(-5.6, 9.5);

        Vector2D e = c.minus(d);
        assertEquals(e, new Vector2D(2.5 + 5.6, 6.5 - 9.5));

        // If the the result is below tolerance, make sure the vector
        // goes to zero
        Vector2D a = new Vector2D(2.5, 6.5);
        // just a tiny bit bigger than a (Note the div 2 to make sure
        // floating point round-off doesn't screw up this test
        Vector2D b = new Vector2D(2.5 + TOL / 2.0, 6.5 + TOL / 2.0);
        e = b.minus(a);
        assertEquals(e, zero);

        // Subtracting a vector below tolerance shouldn't change the vector
        e = b.minus(tolq3);

        assertEquals(e, b);
    }

    @Test
    public void testDotProduct()
    {
        // dot product of orthogonal vectors is 0
        assertEquals(q3a.dot(q3a.getLeftOrtho()), 0.0, TOL);

        // the dot product of a vector and its inverse should be negative
        Vector2D a = new Vector2D(q4d);
        a.negate();
        assertTrue(a.dot(q4d) < 0);

        // Test a value for a known answer
        assertEquals(q1unitb.dot(q1unitd), cosPI6, TOL);

        // play games with threshold vectors
        assertEquals(zero.dot(tolq1), 0.0, TOL);
    }

    @Test
    public void testTimes()
    {
        // check a typical case first
        Vector2D a = new Vector2D(9.5, 6.7);
        Vector2D b = a.times(13);
        assertEquals(b, new Vector2D(9.5 * 13, 6.7 * 13));

        // check that the vector goes to zero
        b = a.times(0);
        assertEquals(b, zero);

        // check the threshold
        Vector2D c = new Vector2D(1, 1);
        b = c.times(TOL / 10.0);
        assertEquals(b, zero);

    }

    @Test
    public void testDiv()
    {
        // division by zero will cause a runtime error, which is hard to catch

        // check a typical case first
        Vector2D a = new Vector2D(42.5, 15.23);
        Vector2D b = a.divide(19.2);
        assertEquals(b, new Vector2D(42.5 / 19.2, 15.23 / 19.2));

        // check the threshold
        Vector2D c = new Vector2D(1, 1);
        b = c.divide(1/(TOL/ 10));
        assertEquals(b, zero);
    }

    @Test
    public void testScalePlusEquals()
    {
        // a Basic computation
        Vector2D base = new Vector2D(1, 1);
        Vector2D toAdd = new Vector2D(5.3, -6.2);
        base.scalePlusEquals(2, toAdd);
        assertEquals(base, new Vector2D(1 + 2 * 5.3, 1 - 2 * 6.2));

        // Make sure that zero scalars leave the result unchanged
        Vector2D original = new Vector2D(base);
        base.scalePlusEquals(0, toAdd);
        assertEquals(base, original);

        // Check to see that a result near zero goes to the zero vector
        base.scalePlusEquals(-(1 - TOL/100), base);
        assertEquals(base, zero);
    }

    @Test
    public void testScalePlus()
    {
        // a Basic computation
        Vector2D base = new Vector2D(1, 1);
        Vector2D toAdd = new Vector2D(5.3, -6.2);
        Vector2D result = base.scalePlus(2, toAdd);
        assertEquals(result, new Vector2D(1 + 2 * 5.3, 1 - 2 * 6.2));

        // Make sure that zero scalars leave the result unchanged
        result = base.scalePlus(0, toAdd);
        assertEquals(result, base);

        // Check to see that a result near zero goes to the zero vector
        result = base.scalePlus(-(1 - TOL/100), base);
        assertEquals(result, zero);
    }

    @Test
    public void testReflectX()
    {
        Vector2D v1 = new Vector2D(1, 5);
        Vector2D v2 = new Vector2D(1, -5);
        Vector2D v3 = new Vector2D(-1, 5);
        Vector2D v4 = new Vector2D(-1, -5);

        v1.reflectX();
        assertEquals(v3, v1);

        v2.reflectX();
        assertEquals(v4, v2);
    }

    @Test
    public void testReflectY()
    {
        Vector2D v1 = new Vector2D(1, 5);
        Vector2D v2 = new Vector2D(1, -5);
        Vector2D v3 = new Vector2D(-1, 5);
        Vector2D v4 = new Vector2D(-1, -5);

        v1.reflectY();
        assertEquals(v2, v1);

        v3.reflectY();
        assertEquals(v4, v3);
    }
}