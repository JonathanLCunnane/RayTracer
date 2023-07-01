import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VectorTest {
    @Test
    @DisplayName("Vector class creates a tuple with w=0")
    public void pointTupleEquivalencyTest()
    {
        Vector p = new Vector(4, -4, 3);
        Tuple t = new Tuple(4, -4, 3, 0);
        Assertions.assertAll(
                () -> Assertions.assertEquals(t, p),
                () -> Assertions.assertEquals(p, t)
        );
    }
}
