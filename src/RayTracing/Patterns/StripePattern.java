package RayTracing.Patterns;

import Display.Colour;
import Tuples.Point;

import java.util.Objects;

public class StripePattern extends ParentPattern{
    public ParentPattern a;
    public ParentPattern b;
    public StripePattern(ParentPattern patternA, ParentPattern patternB)
    {
        a = patternA;
        b = patternB;
    }

    public StripePattern(Colour colourA, Colour colourB)
    {
        a = new SolidPattern(colourA);
        b = new SolidPattern(colourB);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StripePattern that = (StripePattern) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(transform, that.transform);
    }

    public Colour localColourAt(Point localPoint)
    {
        if (Math.floor(localPoint.x) % 2 == 0) return a.localColourAt(localPoint);
        return b.localColourAt(localPoint);
    }
}
