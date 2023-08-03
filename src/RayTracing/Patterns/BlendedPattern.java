package RayTracing.Patterns;

import Display.Colour;
import RayTracing.Objects.ParentObject;
import Tuples.Point;

import java.util.Objects;

public class BlendedPattern extends ParentPattern{
    public ParentPattern a;
    public ParentPattern b;
    public ParentObject obj;
    public BlendedPattern(ParentPattern patternA, ParentPattern patternB, ParentObject object)
    {
        a = patternA;
        b = patternB;
        obj = object;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlendedPattern that = (BlendedPattern) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(transform, that.transform);
    }

    public Colour localColourAt(Point localPoint)
    {
        return a.colourAt(obj, localPoint).plus(b.colourAt(obj, localPoint)).scalarDivide(2);
    }
}
