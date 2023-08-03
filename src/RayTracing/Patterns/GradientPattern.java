package RayTracing.Patterns;

import Display.Colour;
import Tuples.Point;

import java.util.Objects;

public class GradientPattern extends ParentPattern{
    public ParentPattern a;
    public ParentPattern b;
    public GradientPattern(ParentPattern patternA, ParentPattern patternB)
    {
        a = patternA;
        b = patternB;
    }

    public GradientPattern(Colour colourA, Colour colourB)
    {
        a = new SolidPattern(colourA);
        b = new SolidPattern(colourB);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradientPattern that = (GradientPattern) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    public Colour localColourAt(Point localPoint)
    {
        Colour aC = a.localColourAt(localPoint);
        Colour bC = b.localColourAt(localPoint);
        return aC.plus(bC.minus(aC).scalarMultiply(localPoint.x - Math.floor(localPoint.x)));
    }
}
