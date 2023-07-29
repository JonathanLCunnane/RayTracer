package RayTracing;

import Display.Colour;
import Matrices.Matrix;
import Matrices.ScalingMatrix;
import RayTracing.Objects.RayTracerObject;
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
        sOne.setMaterial(sOneM);

        Sphere sTwo = new Sphere();
        Matrix sTwoT = new ScalingMatrix(0.5, 0.5, 0.5);
        sTwo.setTransform(sTwoT);

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
        Colour lighting = w.shadeHit(c);
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
        Colour lighting = w.shadeHit(c);
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
        Colour c = w.colourAt(r);
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
        Colour c = w.colourAt(r);
        Assertions.assertEquals(c, new Colour(0.38066, 0.47583, 0.2855));
    }

    @Test
    @DisplayName("The colour with an intersection behind the ray")
    public void behindRayHit()
    {
        World w = new DefaultWorld();
        RayTracerObject outer = w.objects[0];
        outer.getMaterial().ambient = 1;
        RayTracerObject inner = w.objects[1];
        inner.getMaterial().ambient = 1;
        Ray r = new Ray(
                new Point(0, 0, 0.75),
                new Vector(0, 0, -1)
        );
        Colour c = w.colourAt(r);
        Assertions.assertEquals(c, inner.getMaterial().colour);
    }
}
