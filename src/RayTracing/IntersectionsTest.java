package RayTracing;

import RayTracing.Objects.Sphere;

import Tuples.Point;
import Tuples.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntersectionsTest {
    @Test
    @DisplayName("RayTracing.Intersections should be correctly stored in an RayTracing.Intersections object")
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

    @Test
    @DisplayName("The hit method should work with intersections all with positive time.")
    public void allPosIntersections()
    {
        Sphere s = new Sphere();
        Intersection iOne = new Intersection(s, 1);
        Intersection iTwo = new Intersection(s, 2);
        Intersections xs = new Intersections(
                new Intersection[] { iTwo, iOne }
        );
        Intersection i = xs.hit();
        Assertions.assertEquals(i, iOne);
    }

    @Test
    @DisplayName("The hit method should work with intersections where some have positive/negative time.")
    public void posNegIntersections()
    {
        Sphere s = new Sphere();
        Intersection iOne = new Intersection(s, -1);
        Intersection iTwo = new Intersection(s, 1);
        Intersections xs = new Intersections(
                new Intersection[] { iTwo, iOne }
        );
        Intersection i = xs.hit();
        Assertions.assertEquals(i, iTwo);
    }

    @Test
    @DisplayName("The hit method should return null with intersections where all have negative time.")
    public void allNegIntersections()
    {
        Sphere s = new Sphere();
        Intersection iOne = new Intersection(s, -2);
        Intersection iTwo = new Intersection(s, -1);
        Intersections xs = new Intersections(
                new Intersection[] { iTwo, iOne }
        );
        Intersection i = xs.hit();
        Assertions.assertNull(i);
    }

    @Test
    @DisplayName("The hit method should return the lowest non-negative intersection with differently signed times of intersection.")
    public void variousIntersections()
    {
        Sphere s = new Sphere();
        Intersection iOne = new Intersection(s, 5);
        Intersection iTwo = new Intersection(s, 7);
        Intersection iThree = new Intersection(s, -3);
        Intersection iFour = new Intersection(s, 2);
        Intersections xs = new Intersections(
                new Intersection[] { iOne, iTwo, iThree, iFour }
        );
        Intersection i = xs.hit();
        Assertions.assertEquals(i, iFour);
    }

    @Test
    @DisplayName("The intersect function for World objects")
    public void intersectWorld()
    {
        World w = new DefaultWorld();
        Ray r = new Ray(
                new Point(0, 0, -5),
                new Vector(0, 0, 1)
        );
        Intersections xs = w.intersections(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xs.size, 4),
                () -> Assertions.assertEquals(xs.intersections[0].time, 4),
                () -> Assertions.assertEquals(xs.intersections[1].time, 4.5),
                () -> Assertions.assertEquals(xs.intersections[2].time, 5.5),
                () -> Assertions.assertEquals(xs.intersections[3].time, 6)
        );
    }
}
