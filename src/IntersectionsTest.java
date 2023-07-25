import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntersectionsTest {
    @Test
    @DisplayName("Intersections should be correctly stored in an Intersections object")
    public void intersections()
    {
        Sphere s = new Sphere();
        Intersection iOne = new Intersection(s, 1);
        Intersection iTwo = new Intersection(s, 2);
        Intersection[] xs = new Intersection[] { iOne, iTwo };
        Intersections xss = new Intersections(xs);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xss.size, 2),
                () -> Assertions.assertEquals(xss.intersections[0].time, 1),
                () -> Assertions.assertEquals(xss.intersections[1].time, 2)
        );
    }
}
