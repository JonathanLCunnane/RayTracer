import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointTest {
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
}
