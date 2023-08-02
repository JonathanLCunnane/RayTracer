package RayTracing.Objects;

import RayTracing.Intersections;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaneTest {
    @Test
    @DisplayName("The normal of a plane is constant everywhere.")
    public void constantNormal()
    {
        Plane p = new Plane();
        Vector nOne = p.normalAt(new Point(0, 0, 0));
        Vector nTwo = p.normalAt(new Point(10, 0, -10));
        Vector nThree = p.normalAt(new Point(-5, 0, 150));
        Assertions.assertAll(
                () -> Assertions.assertEquals(nOne, new Vector(0, 1, 0)),
                () -> Assertions.assertEquals(nTwo, new Vector(0, 1, 0)),
                () -> Assertions.assertEquals(nThree, new Vector(0, 1, 0))
        );
    }

    @Test
    @DisplayName("Intersections object should be empty with a ray parallel to the plane")
    public void parallelRay()
    {
        Plane p = new Plane();
        Ray r = new Ray(
                new Point(0, 10, 0),
                new Vector(0, 0, 1)
        );
        Intersections xs = p.localIntersections(r);
        Assertions.assertEquals(xs.size, 0);
    }

    @Test
    @DisplayName("Intersections object should be empty with a ray which is coplanar to the plane")
    public void coplanarRay()
    {
        Plane p = new Plane();
        Ray r = new Ray(
                new Point(0, 0, 0),
                new Vector(0, 0, 1)
        );
        Intersections xs = p.localIntersections(r);
        Assertions.assertEquals(xs.size, 0);
    }

    @Test
    @DisplayName("Ray intersecting from above the plane")
    public void aboveThePlaneRay()
    {
        Plane p = new Plane();
        Ray r = new Ray(
                new Point(0, 1, 0),
                new Vector(0, -1, 0)
        );
        Intersections xs = p.localIntersections(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xs.size, 1),
                () -> Assertions.assertEquals(xs.intersections[0].time, 1),
                () -> Assertions.assertEquals(xs.intersections[0].object, p)
        );
    }

    @Test
    @DisplayName("Ray intersecting from below the plane")
    public void belowThePlaneRay()
    {
        Plane p = new Plane();
        Ray r = new Ray(
                new Point(0, -1, 0),
                new Vector(0, 1, 0)
        );
        Intersections xs = p.localIntersections(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xs.size, 1),
                () -> Assertions.assertEquals(xs.intersections[0].time, 1),
                () -> Assertions.assertEquals(xs.intersections[0].object, p)
        );
    }
}
