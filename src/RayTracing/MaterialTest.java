package RayTracing;

import Display.Colour;
import Tuples.Point;
import Tuples.Vector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MaterialTest {
    @Test
    @DisplayName("The default material")
    public void defaultMaterial()
    {
        Material m = new Material();
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.colour, new Colour(1, 1, 1)),
                () -> Assertions.assertEquals(m.ambient, 0.1),
                () -> Assertions.assertEquals(m.diffuse, 0.9),
                () -> Assertions.assertEquals(m.specular, 0.9),
                () -> Assertions.assertEquals(m.shininess, 200)
        );
    }

    @Test
    @DisplayName("The light source, eye, and normal are in the same line")
    public void inlineReflection()
    {
        Material m = new Material();
        Point point = new Point(0, 0, 0);
        Vector eyeV = new Vector(0, 0, -1);
        Vector normalV = new Vector(0, 0, -1);
        PointLight light = new PointLight(
                new Point(0, 0, -10),
                new Colour(1, 1, 1)
        );
        Colour result = m.lightningAtPoint(light, point, eyeV, normalV, null);
        Assertions.assertEquals(result, new Colour(1.9, 1.9, 1.9));
    }

    @Test
    @DisplayName("The light source, and normal are in the same line, and the eye is at 45 deg or pi/4 rad")
    public void noSpecularReflection()
    {
        Material m = new Material();
        Point point = new Point(0, 0, 0);
        Vector eyeV = new Vector(0, Math.sqrt(2)/2, -Math.sqrt(2)/2);
        Vector normalV = new Vector(0, 0, -1);
        PointLight light = new PointLight(
                new Point(0, 0, -10),
                new Colour(1, 1, 1)
        );
        Colour result = m.lightningAtPoint(light, point, eyeV, normalV, null);
        Assertions.assertEquals(result, new Colour(1, 1, 1));
    }

    @Test
    @DisplayName("The eye, and normal are in the same line, and the light source is at 45 deg or pi/4 rad")
    public void noSpecularLessDiffuseReflection()
    {
        Material m = new Material();
        Point point = new Point(0, 0, 0);
        Vector eyeV = new Vector(0, 0, -1);
        Vector normalV = new Vector(0, 0, -1);
        PointLight light = new PointLight(
                new Point(0, 10, -10),
                new Colour(1, 1, 1)
        );
        Colour result = m.lightningAtPoint(light, point, eyeV, normalV, null);
        Assertions.assertEquals(result, new Colour(0.7364, 0.7364, 0.7364));
    }

    @Test
    @DisplayName("The eye is 45 deg or pi/4 to the normal, and 90 deg or pi/2 to the light source")
    public void LessDiffuseReflection()
    {
        Material m = new Material();
        Point point = new Point(0, 0, 0);
        Vector eyeV = new Vector(0, -Math.sqrt(2)/2, -Math.sqrt(2)/2);
        Vector normalV = new Vector(0, 0, -1);
        PointLight light = new PointLight(
                new Point(0, 10, -10),
                new Colour(1, 1, 1)
        );
        Colour result = m.lightningAtPoint(light, point, eyeV, normalV, null);
        Assertions.assertEquals(result, new Colour(1.6364, 1.6364, 1.6364));
    }

    @Test
    @DisplayName("Lighting with the surface in shadow")
    public void surfaceInShadow()
    {
        Material m = new Material();
        Point point = new Point(0, 0, 0);
        Vector eyeV = new Vector(0, 0, -1);
        Vector normalV = new Vector(0, 0, -1);
        PointLight light = new PointLight(
                new Point(0, 0, -10),
                new Colour(1, 1, 1)
        );
        boolean inShadow = true;
        Colour result = m.lightningAtPoint(light, point, eyeV, normalV, inShadow, null);
        Assertions.assertEquals(result, new Colour(0.1, 0.1, 0.1));
    }
}
