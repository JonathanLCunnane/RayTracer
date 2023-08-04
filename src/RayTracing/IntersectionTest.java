package RayTracing;

import RayTracing.Objects.ParentObject;
import RayTracing.Objects.Plane;
import RayTracing.Objects.Sphere;

import Tuples.Point;
import Tuples.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntersectionTest {
    @Test
    @DisplayName("An intersection should be properly represented")
    public void intersection()
    {
        Sphere s = new Sphere();
        Intersection i = new Intersection(s, 3.5);
        Assertions.assertAll(
                () -> Assertions.assertEquals(i.time, 3.5),
                () -> Assertions.assertEquals(i.object, s)
        );
    }

    @Test
    @DisplayName("Computations should be correctly prepared and stored for an outside ray intersection")
    public void outsideIntersection()
    {
        Ray r = new Ray(
                new Point(0, 0, -5),
                new Vector(0, 0, 1)
        );
        Sphere s = new Sphere();
        Intersection i = new Intersection(s, 4);
        Computations c = i.prepareComputations(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(c.time, i.time),
                () -> Assertions.assertEquals(c.object, i.object),
                () -> Assertions.assertEquals(c.point, new Point(0, 0, -1)),
                () -> Assertions.assertEquals(c.eyeV, new Vector(0, 0, -1)),
                () -> Assertions.assertEquals(c.normalV, new Vector(0, 0, -1)),
                () -> Assertions.assertFalse(c.inside)
        );
    }

    @Test
    @DisplayName("Computations should be correctly prepared and stored for an inside ray intersection")
    public void insideIntersection()
    {
        Ray r = new Ray(
                new Point(0, 0, 0),
                new Vector(0, 0, 1)
        );
        Sphere s = new Sphere();
        Intersection i = new Intersection(s, 1);
        Computations c = i.prepareComputations(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(c.time, i.time),
                () -> Assertions.assertEquals(c.object, i.object),
                () -> Assertions.assertEquals(c.point, new Point(0, 0, 1)),
                () -> Assertions.assertEquals(c.eyeV, new Vector(0, 0, -1)),
                () -> Assertions.assertEquals(c.normalV, new Vector(0, 0, -1)),
                () -> Assertions.assertTrue(c.inside)
        );
    }


    @Test
    @DisplayName("Computations object correctly calculates the reflected vector.")
    public void reflectedVectorCalculation()
    {
        ParentObject p = new Plane();
        Ray r = new Ray(
                new Point(0, 1, -1),
                new Vector(0, -Math.sqrt(2)/2, Math.sqrt(2)/2)
        );
        Intersection i = new Intersection(p, Math.sqrt(2));
        Computations c = i.prepareComputations(r);
        Assertions.assertEquals(c.reflectV, new Vector(0, Math.sqrt(2)/2, Math.sqrt(2)/2));
    }
}
