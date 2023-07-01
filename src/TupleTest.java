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
}
