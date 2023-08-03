package RayTracing.Patterns;

import Display.Colour;
import Tuples.Point;

import java.util.Objects;

public class CheckersPattern extends ParentPattern{
    public ParentPattern a;
    public ParentPattern b;
    public CheckersPattern(ParentPattern patternA, ParentPattern patternB)
    {
        a = patternA;
        b = patternB;
    }

    public CheckersPattern(Colour colourA, Colour colourB)
    {
        a = new SolidPattern(colourA);
        b = new SolidPattern(colourB);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckersPattern that = (CheckersPattern) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    public Colour localColourAt(Point localPoint)
    {
        if ((Math.floor(localPoint.x) + Math.floor(localPoint.y) + Math.floor(localPoint.z)) % 2 == 0) return a.localColourAt(localPoint);
        return b.localColourAt(localPoint);
    }
}
