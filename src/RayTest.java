import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RayTest {
    @Test
    @DisplayName("A ray should be properly represented")
    public void rayRepresentation()
    {
        Point origin = new Point(1, 2, 3);
        Vector direction = new Vector(4, 5, 6);
        Ray r = new Ray(origin, direction);
        Assertions.assertAll(
                () -> Assertions.assertEquals(r.origin, origin),
                () -> Assertions.assertEquals(r.direction, direction)
        );
    }

    @Test
    @DisplayName("A point at a certain 'time' should be properly calculated")
    public void positionAtTimeT()
    {
        Point origin = new Point(2, 3, 4);
        Vector direction = new Vector(1, 0, 0);
        Ray r = new Ray(origin, direction);
        Assertions.assertAll(
                () -> Assertions.assertEquals(r.position(0), new Point(2, 3, 4)),
                () -> Assertions.assertEquals(r.position(1), new Point(3, 3, 4)),
                () -> Assertions.assertEquals(r.position(-1), new Point(1, 3, 4)),
                () -> Assertions.assertEquals(r.position(2.5), new Point(4.5, 3, 4))
        );
    }
}
