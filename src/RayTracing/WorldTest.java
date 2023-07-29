package RayTracing;

import Display.Colour;
import Matrices.Matrix;
import Matrices.ScalingMatrix;
import RayTracing.Objects.Sphere;
import Tuples.Point;
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
}
