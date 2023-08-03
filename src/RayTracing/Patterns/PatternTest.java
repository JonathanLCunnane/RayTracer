package RayTracing.Patterns;

import Display.Colour;
import Matrices.ScalingMatrix;
import Matrices.TranslationMatrix;
import RayTracing.Material;
import RayTracing.Objects.Sphere;
import RayTracing.PointLight;
import Tuples.Point;
import Tuples.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PatternTest {
    @Test
    @DisplayName("Creating a stripe pattern")
    public void stripePatternCreation()
    {
        Colour black = new Colour(0, 0, 0);
        Colour white = new Colour(1, 1, 1);
        StripePattern p = new StripePattern(white, black);
        Assertions.assertAll(
                () -> Assertions.assertEquals(p.a, new SolidPattern(white)),
                () -> Assertions.assertEquals(p.b, new SolidPattern(black))
        );
    }

    @Test
    @DisplayName("A stripe pattern alternates in x.")
    public void stripePatternAlternatesInX()
    {
        Colour black = new Colour(0, 0, 0);
        Colour white = new Colour(1, 1, 1);
        StripePattern p = new StripePattern(white, black);
        Assertions.assertAll(
                () -> Assertions.assertEquals(p.localColourAt(new Point(0, 0, 0)), white),
                () -> Assertions.assertEquals(p.localColourAt(new Point(0.9, 0, 1)), white),
                () -> Assertions.assertEquals(p.localColourAt(new Point(1, 0, 2)), black),
                () -> Assertions.assertEquals(p.localColourAt(new Point(-0.1, 0, 0)), black),
                () -> Assertions.assertEquals(p.localColourAt(new Point(-1, 0, 1)), black),
                () -> Assertions.assertEquals(p.localColourAt(new Point(-1.1, 0, 2)), white)
        );
    }

    @Test
    @DisplayName("A stripe pattern is constant in y.")
    public void stripePatternConstantInY()
    {
        Colour black = new Colour(0, 0, 0);
        Colour white = new Colour(1, 1, 1);
        StripePattern p = new StripePattern(white, black);
        Assertions.assertAll(
                () -> Assertions.assertEquals(p.localColourAt(new Point(0, 0, 0)), white),
                () -> Assertions.assertEquals(p.localColourAt(new Point(0, 1, 0)), white),
                () -> Assertions.assertEquals(p.localColourAt(new Point(0, 2, 0)), white)
        );
    }

    @Test
    @DisplayName("A stripe pattern is constant in z.")
    public void stripePatternConstantInZ()
    {
        Colour black = new Colour(0, 0, 0);
        Colour white = new Colour(1, 1, 1);
        StripePattern p = new StripePattern(white, black);
        Assertions.assertAll(
                () -> Assertions.assertEquals(p.localColourAt(new Point(0, 0, 0)), white),
                () -> Assertions.assertEquals(p.localColourAt(new Point(0, 0, 1)), white),
                () -> Assertions.assertEquals(p.localColourAt(new Point(0, 0, 2)), white)
        );
    }

    @Test
    @DisplayName("Lighting with a pattern applied.")
    public void lightingPattern()
    {
        Material m = new Material();
        Sphere dummyObj = new Sphere();
        m.pattern = new StripePattern(
                new Colour(1, 1, 1),
                new Colour(0, 0, 0)
        );
        m.ambient = 1;
        m.diffuse = 0;
        m.specular = 0;
        Vector eyeV = new Vector(0, 0, -1);
        Vector normalV = new Vector(0, 0, -1);
        PointLight pl = new PointLight(
                new Point(0, 0, -10),
                new Colour(1, 1, 1)
        );
        Colour one = m.lightningAtPoint(pl, new Point(0.9, 0, 0), eyeV, normalV, false, dummyObj);
        Colour two = m.lightningAtPoint(pl, new Point(1.1, 0, 0), eyeV, normalV, false, dummyObj);
        Assertions.assertAll(
                () -> Assertions.assertEquals(one, new Colour(1, 1, 1)),
                () -> Assertions.assertEquals(two, new Colour(0, 0, 0))
        );
    }

    @Test
    @DisplayName("Stripes with an object transformation")
    public void objectTransformationStripes()
    {
        Sphere s = new Sphere();
        s.transform = new ScalingMatrix(2, 2, 2);
        ParentPattern p = new StripePattern(new Colour(1, 1, 1), new Colour(0, 0, 0));
        Colour c = p.colourAt(s, new Point(1.5, 0, 0));
        Assertions.assertEquals(c, new Colour(1, 1, 1));
    }

    @Test
    @DisplayName("Stripes with a pattern transformation")
    public void patternTransformationStripes()
    {
        Sphere s = new Sphere();
        ParentPattern p = new StripePattern(new Colour(1, 1, 1), new Colour(0, 0, 0));
        p.transform = new ScalingMatrix(2, 2, 2);
        Colour c = p.colourAt(s, new Point(1.5, 0, 0));
        Assertions.assertEquals(c, new Colour(1, 1, 1));
    }

    @Test
    @DisplayName("Stripes with a pattern and an object transformation")
    public void patternAndObjectTransformationStripes()
    {
        Sphere s = new Sphere();
        s.transform = new ScalingMatrix(2, 2, 2);
        ParentPattern p = new StripePattern(new Colour(1, 1, 1), new Colour(0, 0, 0));
        p.transform = new TranslationMatrix(0.5, 0, 0);
        Colour c = p.colourAt(s, new Point(2.5, 0, 0));
        Assertions.assertEquals(c, new Colour(1, 1, 1));
    }
}
