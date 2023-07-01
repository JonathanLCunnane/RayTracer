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
}
