package RayTracing;

import Matrices.ScalingMatrix;
import Matrices.TranslationMatrix;
import RayTracing.Objects.GlassSphere;
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

    @Test
    @DisplayName("Preparing refraction computations works correctly")
    public void refractionComputations()
    {
        Sphere a = new GlassSphere();
        a.transform = new ScalingMatrix(2, 2, 2);
        Sphere b = new GlassSphere();
        b.transform = new TranslationMatrix(0, 0, -0.25);
        b.material.refractiveIndex = 2;
        Sphere c = new GlassSphere();
        c.transform = new TranslationMatrix(0, 0, 0.25);
        c.material.refractiveIndex = 2.5;
        Ray r = new Ray(
                new Point(0, 0, -4),
                new Vector(0, 0, 1)
        );
        Intersections xs = new Intersections(
                new Intersection[]
                        {
                                new Intersection(a, 2),
                                new Intersection(b, 2.75),
                                new Intersection(c, 3.25),
                                new Intersection(b, 4.75),
                                new Intersection(c, 5.25),
                                new Intersection(a, 6),
                        }
        );
        double[][] results = new double[][]
                {
                        {1, 1.52},
                        {1.52, 2},
                        {2, 2.5},
                        {2.5, 2.5},
                        {2.5, 1.52},
                        {1.52, 1},
                };
        Computations comps;
        for (int i = 0; i < xs.size; i++)
        {
            comps = xs.intersections[i].prepareRefractionComputations(r, xs);
            int finalI = i;
            Computations finalComps = comps;
            Assertions.assertAll(
                    () -> Assertions.assertEquals(finalComps.nOne, results[finalI][0]),
                    () -> Assertions.assertEquals(finalComps.nTwo, results[finalI][1])
            );
        }
    }
}
