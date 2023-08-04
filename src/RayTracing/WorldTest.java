package RayTracing;

import Display.Colour;
import Matrices.ScalingMatrix;
import Matrices.TranslationMatrix;
import RayTracing.Objects.ParentObject;
import RayTracing.Objects.Plane;
import RayTracing.Objects.Sphere;
import Tuples.Point;
import Tuples.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorldTest {
    @Test
    @DisplayName("When a world is instantiated with no parameters, it has no objects and no light source")
    public void noParamWorld()
    {
        World w = new World();
        Assertions.assertAll(
                () -> Assertions.assertEquals(w.objects.length, 0),
                () -> Assertions.assertNull(w.light)
        );
    }

    @Test
    @DisplayName("The default world has the correct objects and light source")
    public void defaultWorld()
    {
        World w = new DefaultWorld();
        PointLight light = new PointLight(
                new Point(-10, 10, -10),
                new Colour(1, 1, 1)
        );
        Sphere sOne = new Sphere();
        Material sOneM = new Material();
        sOneM.colour = new Colour(0.8, 1.0, 0.6);
        sOneM.diffuse = 0.7;
        sOneM.specular = 0.2;
        sOne.material = sOneM;

        Sphere sTwo = new Sphere();
        sTwo.transform = new ScalingMatrix(0.5, 0.5, 0.5);

        Assertions.assertAll(
                () -> Assertions.assertEquals(w.light, light),
                () -> Assertions.assertTrue(w.contains(sOne)),
                () -> Assertions.assertTrue(w.contains(sTwo))
        );
    }

    @Test
    @DisplayName("Shade on hit function outside an object")
    public void shadeHitOutside()
    {
        World w = new DefaultWorld();
        Ray r = new Ray(
                new Point(0, 0, -5),
                new Vector(0, 0, 1)
        );
        Intersection i = new Intersection(w.objects[0], 4);
        Computations c = i.prepareComputations(r);
        Colour lighting = w.shadeHit(c, 5);
        Assertions.assertEquals(lighting, new Colour(0.38066, 0.47583, 0.2855));
    }

    @Test
    @DisplayName("Shade on hit function inside an object")
    public void shadeHitInside()
    {
        World w = new DefaultWorld();
        w.light = new PointLight(
                new Point(0, 0.25, 0),
                new Colour(1, 1, 1)
        );
        Ray r = new Ray(
                new Point(0, 0, 0),
                new Vector(0, 0, 1)
        );
        Intersection i = new Intersection(w.objects[1], 0.5);
        Computations c = i.prepareComputations(r);
        Colour lighting = w.shadeHit(c, 5);
        Assertions.assertEquals(lighting, new Colour(0.90498, 0.90498, 0.90498));
    }

    @Test
    @DisplayName("Colour at a certain ray using colourAt when a ray misses")
    public void rayMisses()
    {
        World w = new DefaultWorld();
        Ray r = new Ray(
                new Point(0, 0, -5),
                new Vector(0, 1, 0)
        );
        Colour c = w.colourAt(r, 5);
        Assertions.assertEquals(c, new Colour(0, 0, 0));
    }

    @Test
    @DisplayName("Colour at a certain ray using colourAt when a ray hits")
    public void rayHits()
    {
        World w = new DefaultWorld();
        Ray r = new Ray(
                new Point(0, 0, -5),
                new Vector(0, 0, 1)
        );
        Colour c = w.colourAt(r, 5);
        Assertions.assertEquals(c, new Colour(0.38066, 0.47583, 0.2855));
    }

    @Test
    @DisplayName("The colour with an intersection behind the ray")
    public void behindRayHit()
    {
        World w = new DefaultWorld();
        ParentObject outer = w.objects[0];
        outer.material.ambient = 1;
        ParentObject inner = w.objects[1];
        inner.material.ambient = 1;
        Ray r = new Ray(
                new Point(0, 0, 0.75),
                new Vector(0, 0, -1)
        );
        Colour c = w.colourAt(r, 5);
        Assertions.assertEquals(c, inner.material.colour);
    }

    @Test
    @DisplayName("There is no shadow when nothing is co-linear with the point and light")
    public void noShadow()
    {
        World w = new DefaultWorld();
        Point p = new Point(0, 10, 0);
        Assertions.assertFalse(w.isInShadow(p));
    }

    @Test
    @DisplayName("There is a shadow when an object is in between the light and the point")
    public void shadow()
    {
        World w = new DefaultWorld();
        Point p = new Point(10, -10, 10);
        Assertions.assertTrue(w.isInShadow(p));
    }

    @Test
    @DisplayName("There is no shadow when the light source is in between the point and an object")
    public void noShadowCoLinear()
    {
        World w = new DefaultWorld();
        Point p = new Point(-20, 20, -20);
        Assertions.assertFalse(w.isInShadow(p));
    }

    @Test
    @DisplayName("There is no shadow when the object is behind the point")
    public void noShadowCoLinearTwo()
    {
        World w = new DefaultWorld();
        Point p = new Point(-2, 2, -2);
        Assertions.assertFalse(w.isInShadow(p));
    }

    @Test
    @DisplayName("The shadeHit() method works given an intersection in shadow")
    public void shadeHitInShadow()
    {
        World w = new World();
        w.light = new PointLight(
                new Point(0, 0, -10),
                new Colour(1, 1, 1)
        );
        Sphere second = new Sphere();
        second.transform = new TranslationMatrix(0, 0, 10);
        w.objects = new ParentObject[]
                {
                        new Sphere(),
                        second
                };
        Ray r = new Ray(
                new Point(0, 0, 5),
                new Vector(0, 0, 1)
        );
        Computations comps = w.intersections(r).hit().prepareComputations(r);
        Colour c = w.shadeHit(comps, 5);
        Assertions.assertEquals(c, new Colour(0.1, 0.1, 0.1));
    }

    @Test
    @DisplayName("Ray striking a non-reflective surface")
    public void nonReflectiveSurface()
    {
        World w = new DefaultWorld();
        Ray r = new Ray(
                new Point(0, 0, 0),
                new Vector(0, 0, 1)
        );
        w.objects[1].material.ambient = 1;
        Intersection i = new Intersection(w.objects[1], 1);
        Computations c = i.prepareComputations(r);
        Colour result = w.reflectedColour(c, 5);
        Assertions.assertEquals(result, new Colour(0, 0, 0));
    }

    @Test
    @DisplayName("Ray striking a reflective surface")
    public void reflectiveSurface()
    {
        World w = new DefaultWorld();
        ParentObject p = new Plane();
        p.material.reflectiveness = 0.5;
        p.transform = new TranslationMatrix(0, -1, 0);
        w.objects = new ParentObject[]
                {
                        w.objects[0],
                        w.objects[1],
                        p
                };
        Ray r = new Ray(
                new Point(0, 0, -3),
                new Vector(0, -Math.sqrt(2)/2, Math.sqrt(2)/2)
        );
        Intersection i = new Intersection(p, Math.sqrt(2));
        Computations c = i.prepareComputations(r);
        Colour result = w.reflectedColour(c, 5);
        Assertions.assertEquals(result, new Colour(0.19033, 0.23792, 0.14275));
    }

    @Test
    @DisplayName("shadeHit function with a reflective material")
    public void shadeHitReflective()
    {
        World w = new DefaultWorld();
        ParentObject p = new Plane();
        p.material.reflectiveness = 0.5;
        p.transform = new TranslationMatrix(0, -1, 0);
        w.objects = new ParentObject[]
                {
                        w.objects[0],
                        w.objects[1],
                        p
                };
        Ray r = new Ray(
                new Point(0, 0, -3),
                new Vector(0, -Math.sqrt(2)/2, Math.sqrt(2)/2)
        );
        Intersection i = new Intersection(p, Math.sqrt(2));
        Computations c = i.prepareComputations(r);
        Colour result = w.shadeHit(c, 5);
        Assertions.assertEquals(result, new Colour(0.87676, 0.92434, 0.82917));
    }

    @Test
    @DisplayName("colourAt with 'infinite' reflections.")
    public void recursionOfReflections()
    {
        World w = new World();
        w.light = new PointLight(
                new Point(0, 0, 0),
                new Colour(1, 1, 1)
        );
        ParentObject lowerMirror = new Plane();
        lowerMirror.material.reflectiveness = 1;
        lowerMirror.transform = new TranslationMatrix(0, -1, 0);
        ParentObject upperMirror = new Plane();
        upperMirror.material.reflectiveness = 1;
        upperMirror.transform = new TranslationMatrix(0, 1, 0);
        w.objects = new ParentObject[]
                {
                        lowerMirror,
                        upperMirror
                };
        Ray r = new Ray(
                new Point(0, 0, 0),
                new Vector(0, 1, 0)
        );
        Assertions.assertDoesNotThrow(() -> w.colourAt(r, 5));
    }
}
