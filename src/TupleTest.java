import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TupleTest {
    @Test
    @DisplayName("A tuple with w=1.0 is a point")
    public void pointTest()
    {
        Tuple t = new Tuple(4.3, -4.2, 3.1, 1.0);
        Assertions.assertAll(
                () -> Assertions.assertEquals(t.x, 4.3),
                () -> Assertions.assertEquals(t.y, -4.2),
                () -> Assertions.assertEquals(t.z, 3.1),
                () -> Assertions.assertEquals(t.w, 1.0),
                () -> Assertions.assertTrue(t.isPoint()),
                () -> Assertions.assertFalse(t.isVector())
        );
    }

    @Test
    @DisplayName("A tuple with w=0 is a vector")
    public void vectorTest()
    {
        Tuple t = new Tuple(4.3, -4.2, 3.1, 0.0);
        Assertions.assertAll(
                () -> Assertions.assertEquals(t.x, 4.3),
                () -> Assertions.assertEquals(t.y, -4.2),
                () -> Assertions.assertEquals(t.z, 3.1),
                () -> Assertions.assertEquals(t.w, 0.0),
                () -> Assertions.assertFalse(t.isPoint()),
                () -> Assertions.assertTrue(t.isVector())
        );
    }

    @Test
    @DisplayName("Point class creates a tuple with w=1.0")
    public void pointTupleEquivalencyTest()
    {
        Point p = new Point(4, -4, 3);
        Tuple t = new Tuple(4, -4, 3, 1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(t, p),
                () -> Assertions.assertEquals(p, t)
        );
    }

    @Test
    @DisplayName("Vector class creates a tuple with w=0")
    public void vectorTupleEquivalencyTest()
    {
        Vector v = new Vector(4, -4, 3);
        Tuple t = new Tuple(4, -4, 3, 0);
        Assertions.assertAll(
                () -> Assertions.assertEquals(t, v),
                () -> Assertions.assertEquals(v, t)
        );
    }

    @Test
    @DisplayName("Adding a point and a vector, and a vector and a vector")
    public void tupleAdditionTest()
    {
        Point p = new Point(3, -2, 5);
        Vector vOne = new Vector(4, -4, 3);
        Vector vTwo = new Vector(-2, 3, 1);

        Tuple pvResult = new Tuple(7, -6, 8, 1);
        Tuple vvResult = new Tuple(2, -1, 4, 0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(p.plus(vOne), pvResult),
                () -> Assertions.assertEquals(vOne.plus(vTwo), vvResult)
        );
    }

    @Test
    @DisplayName("Subtracting a point from a point, a vector from a point, and a vector from a vector")
    public void tupleSubtractionTest()
    {
        Point pOne = new Point(3, -2, 5);
        Point pTwo = new Point(3, 2, 1);
        Vector vOne = new Vector(4, -4, 3);
        Vector vTwo = new Vector(-2, 3, 1);

        Tuple ppResult = new Tuple(0, -4, 4, 0);
        Tuple pvResult = new Tuple(-1, 2, 2, 1);
        Tuple vvResult = new Tuple(6, -7, 2, 0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(pOne.minus(pTwo), ppResult),
                () -> Assertions.assertEquals(pOne.minus(vOne), pvResult),
                () -> Assertions.assertEquals(vOne.minus(vTwo), vvResult)
        );
    }

    @Test
    @DisplayName("Negating a vector")
    public void negateVectorTest()
    {
        Vector v = new Vector(1, -2, 3);
        v = v.negate();
        Vector result = new Vector(-1, 2, -3);
        Assertions.assertEquals(v, result);
    }

    @Test
    @DisplayName("Multiplying a vector by a scalar")
    public void scalarMultiplyVectorTest()
    {
        Vector v = new Vector(1, -2, 3);
        v = v.scalarMultiply(3.5);
        Vector result = new Vector(3.5, -7, 10.5);
        Assertions.assertEquals(v, result);
    }

    @Test
    @DisplayName("Dividing a vector by a scalar")
    public void scalarDivideVectorTest()
    {
        Vector v = new Vector(1, -2, 3);
        v = v.scalarDivide(2);
        Vector result = new Vector(0.5, -1, 1.5);
        Assertions.assertEquals(v, result);
    }

    @Test
    @DisplayName("Finding the magnitude of a vector")
    public void vectorMagnitudeTest()
    {
        Vector vOne = new Vector(1, 0, 0);
        Vector vTwo = new Vector(0, 1, 0);
        Vector vThree = new Vector(0, 0, 1);
        Vector vFour = new Vector(1, 2, 3);
        Vector vFive = new Vector(-1, -2, -3);
        Assertions.assertAll(
                () -> Assertions.assertEquals(vOne.magnitude(), 1),
                () -> Assertions.assertEquals(vTwo.magnitude(), 1),
                () -> Assertions.assertEquals(vThree.magnitude(), 1),
                () -> Assertions.assertEquals(vFour.magnitude(), Math.pow(14, 0.5)),
                () -> Assertions.assertEquals(vFive.magnitude(), Math.pow(14, 0.5))
        );
    }

    @Test
    @DisplayName("Finding the normalised vector of a vector")
    public void vectorNormalisationTest()
    {
        Vector vOne = new Vector(4, 0, 0);
        Vector vTwo = new Vector(1, 2, 3);
        Vector vResultOne = new Vector(1, 0, 0);
        Vector vResultTwo = new Vector(1/Math.pow(14, 0.5), 2/Math.pow(14, 0.5), 3/Math.pow(14, 0.5));
        Assertions.assertAll(
                () -> Assertions.assertEquals(vOne.normalised(), vResultOne),
                () -> Assertions.assertEquals(vTwo.normalised(), vResultTwo),
                () -> Assertions.assertEquals(vTwo.normalised().magnitude(), 1)
        );
    }

    @Test
    @DisplayName("Dot product of two vectors")
    public void vectorDotProductTest()
    {
        Vector vOne = new Vector(1, 2, 3);
        Vector vTwo = new Vector(2, 3, 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(vOne.dot(vTwo), vTwo.dot(vOne)),
                () -> Assertions.assertEquals(vOne.dot(vTwo), 20)
        );

    }

    @Test
    @DisplayName("Cross product of two vectors")
    public void vectorCrossProductTest()
    {
        Vector vOne = new Vector(1, 2, 3);
        Vector vTwo = new Vector(2, 3, 4);
        Vector vResultOne = new Vector(-1, 2, -1);
        Vector vResultTwo = new Vector(1, -2, 1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(vOne.cross(vTwo), vResultOne),
                () -> Assertions.assertEquals(vTwo.cross(vOne), vResultTwo)
        );
    }

    @Test
    @DisplayName("Reflecting a vector approaching at 45 deg or pi/4 rad")
    public void piOnFourReflection()
    {
        Vector v = new Vector(1, -1, 0);
        Vector n = new Vector(0, 1, 0);
        Vector r = v.reflect(n);
        Assertions.assertEquals(r, new Vector(1, 1, 0));
    }

    @Test
    @DisplayName("Reflecting a vector at a different angle")
    public void otherReflection()
    {
        Vector v = new Vector(0, -1, 0);
        Vector n = new Vector(Math.sqrt(2)/2, Math.sqrt(2)/2, 0);
        Vector r = v.reflect(n);
        Assertions.assertEquals(r, new Vector(1, 0, 0));
    }
}
