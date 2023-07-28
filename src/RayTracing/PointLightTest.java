package RayTracing;

import Display.Colour;
import Tuples.Point;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointLightTest {
    @Test
    @DisplayName("A point light has position and intensity")
    public void defaultPointLight()
    {
        Colour intensity = new Colour(1, 1, 1);
        Point position = new Point(0, 0, 0);
        PointLight pl = new PointLight(position, intensity);
        Assertions.assertAll(
                () -> Assertions.assertEquals(pl.position, position),
                () -> Assertions.assertEquals(pl.intensity, intensity)
        );
    }
}
