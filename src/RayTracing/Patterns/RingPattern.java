package RayTracing.Patterns;

import Display.Colour;
import Tuples.Point;

import java.util.Objects;

public class RingPattern extends ParentPattern{
    public ParentPattern a;
    public ParentPattern b;
    public RingPattern(ParentPattern patternA, ParentPattern patternB)
    {
        a = patternA;
        b = patternB;
    }

    public RingPattern(Colour colourA, Colour colourB)
    {
        a = new SolidPattern(colourA);
        b = new SolidPattern(colourB);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RingPattern that = (RingPattern) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(transform, that.transform);
    }

    public Colour localColourAt(Point localPoint)
    {
        if (Math.floor(Math.sqrt((localPoint.x * localPoint.x) + (localPoint.z * localPoint.z))) % 2 == 0) return a.localColourAt(localPoint);
        return b.localColourAt(localPoint);
    }
}
