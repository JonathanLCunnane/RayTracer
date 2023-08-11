package RayTracing.Objects;

import Matrices.*;
import RayTracing.Intersections;
import RayTracing.Material;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Vector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SphereTest {
    @Test
    @DisplayName("RayTracing.Ray intersecting the unit sphere at two distinct points")
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
    @DisplayName("RayTracing.Ray intersecting the unit sphere as a tangent")
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
    @DisplayName("RayTracing.Ray starting inside the sphere intersecting at two distinct points")
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
        s.transform = m;
        Assertions.assertEquals(s.transform, m);
    }

    @Test
    @DisplayName("A scaled sphere should still be able to correctly produce intersection values")
    public void scaledSphereIntersections()
    {
        Sphere s = new Sphere();
        Ray r = new Ray(
                new Point(0, 0, -5),
                new Vector(0, 0, 1)
        );
        s.transform = new ScalingMatrix(2, 2, 2);
        Intersections xs = s.intersections(r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(xs.size, 2),
                () -> Assertions.assertEquals(xs.intersections[0].time, 3),
                () -> Assertions.assertEquals(xs.intersections[1].time, 7)
        );
    }

    @Test
    @DisplayName("A translated sphere should still be able to correctly produce intersection values")
    public void translatedSphereIntersections()
    {
        Sphere s = new Sphere();
        Ray r = new Ray(
                new Point(0, 0, -5),
                new Vector(0, 0, 1)
        );
        s.transform = new TranslationMatrix(5, 0, 0);
        Intersections xs = s.intersections(r);
        Assertions.assertEquals(xs.size, 0);
    }

    @Test
    @DisplayName("The normal on a sphere at a point on the x axis")
    public void xAxisNormal()
    {
        Sphere s = new Sphere();
        Vector n = s.normalAt(new Point(1, 0, 0));
        Assertions.assertEquals(n, new Vector(1, 0, 0));
    }

    @Test
    @DisplayName("The normal on a sphere at a point on the y axis")
    public void yAxisNormal()
    {
        Sphere s = new Sphere();
        Vector n = s.normalAt(new Point(0, 1, 0));
        Assertions.assertEquals(n, new Vector(0, 1, 0));
    }

    @Test
    @DisplayName("The normal on a sphere at a point on the z axis")
    public void zAxisNormal()
    {
        Sphere s = new Sphere();
        Vector n = s.normalAt(new Point(0, 0, 1));
        Assertions.assertEquals(n, new Vector(0, 0, 1));
    }

    @Test
    @DisplayName("The normal on a sphere at a certain point is correct and normalised")
    public void certainPointNormal()
    {
        Sphere s = new Sphere();
        Vector n = s.normalAt(new Point(Math.sqrt(3)/3, Math.sqrt(3)/3, Math.sqrt(3)/3));
        Assertions.assertAll(
                () -> Assertions.assertEquals(n, new Vector(Math.sqrt(3)/3, Math.sqrt(3)/3, Math.sqrt(3)/3)),
                () -> Assertions.assertEquals(n, n.normalised())
        );
    }

    @Test
    @DisplayName("The normal on a translated sphere")
    public void translatedSphereNormal()
    {
        Sphere s = new Sphere();
        s.transform = new TranslationMatrix(0, 1, 0);
        Vector n = s.normalAt(new Point(0, 1.70711, -0.70711));
        Assertions.assertEquals(n, new Vector(0, 0.70711, -0.70711));
    }

    @Test
    @DisplayName("The normal on a transformed sphere")
    public void transformedSphereNormal()
    {
        Sphere s = new Sphere();
        s.transform = new ScalingMatrix(1, 0.5, 1).times(new ZRotationMatrix(Math.PI/5));
        Vector n = s.normalAt(new Point(0, Math.sqrt(2)/2, -Math.sqrt(2)/2));
        Assertions.assertEquals(n, new Vector(0, 0.97014, -0.24254));
    }

    @Test
    @DisplayName("A sphere has a default material")
    public void defaultMaterial()
    {
        Sphere s = new Sphere();
        Assertions.assertEquals(s.material, new Material());
    }

    @Test
    @DisplayName("A sphere's material may be assigned")
    public void assigningMaterial()
    {
        Sphere s = new Sphere();
        Material m = new Material();
        m.ambient = 1;
        s.material = m;
        Assertions.assertEquals(s.material, m);
    }

    @Test
    @DisplayName("Default glass sphere")
    public void defaultGlassSphere()
    {
        Sphere s = new GlassSphere();
        Assertions.assertAll(
                () -> Assertions.assertEquals(s.transform, new IdentityMatrix(4)),
                () -> Assertions.assertEquals(s.material.transparency, 0.9),
                () -> Assertions.assertEquals(s.material.reflectiveness, 0.9),
                () -> Assertions.assertEquals(s.material.refractiveIndex, 1.52),
                () -> Assertions.assertEquals(s.material.diffuse, 0.1),
                () -> Assertions.assertEquals(s.material.ambient, 0.1),
                () -> Assertions.assertEquals(s.material.specular, 1),
                () -> Assertions.assertEquals(s.material.shininess, 300),
                () -> Assertions.assertFalse(s.material.castsShadow)
        );
    }
}
