package RayTracing.Patterns;

import Display.Colour;
import Tuples.Point;

import java.util.Objects;

public class BlendedPattern extends ParentPattern{
    public ParentPattern a;
    public ParentPattern b;
    public BlendedPattern(ParentPattern patternA, ParentPattern patternB)
    {
        a = patternA;
        b = patternB;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlendedPattern that = (BlendedPattern) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    public Colour localColourAt(Point localPoint)
    {
        return a.localColourAt(localPoint).plus(b.localColourAt(localPoint)).scalarDivide(2);
    }
}
