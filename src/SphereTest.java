import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SphereTest {
    @Test
    @DisplayName("Ray intersecting the unit sphere at two distinct points")
    public void doubleIntersection()
    {
        Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere s = new Sphere();
        Intersections xs = s.intersections(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xs.size, 2),
                () -> Assertions.assertEquals(xs.intersections[0].time, 4),
                () -> Assertions.assertEquals(xs.intersections[1].time, 6),
                () -> Assertions.assertEquals(xs.intersections[0].object, s), // Check that the object is correctly
                () -> Assertions.assertEquals(xs.intersections[1].object, s)  // allocated just in this test
        );
    }

    @Test
    @DisplayName("Ray intersecting the unit sphere as a tangent")
    public void tangentIntersection()
    {
        Ray r = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
        Sphere s = new Sphere();
        Intersections xs = s.intersections(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xs.size, 2),
                () -> Assertions.assertEquals(xs.intersections[0].time, 5),
                () -> Assertions.assertEquals(xs.intersections[1].time, 5)
        );
    }

    @Test
    @DisplayName("Ray starting inside the sphere intersecting at two distinct points")
    public void internalRayIntersection()
    {
        Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Sphere s = new Sphere();
        Intersections xs = s.intersections(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xs.size, 2),
                () -> Assertions.assertEquals(xs.intersections[0].time, -1),
                () -> Assertions.assertEquals(xs.intersections[1].time, 1)
        );
    }

    @Test
    @DisplayName("A sphere should have a correct default transformation of the identity matrix")
    public void defaultTransform()
    {
        Sphere s = new Sphere();
        Assertions.assertEquals(s.transform, new IdentityMatrix(4));
    }

    @Test
    @DisplayName("A sphere should be able to have new transforms correctly applied")
    public void changingSphereTransform()
    {
        Sphere s = new Sphere();
        Matrix m = new TranslationMatrix(2, 3, 4);
        s.setTransform(m);
        Assertions.assertEquals(s.transform, m);
    }
}
