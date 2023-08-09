package RayTracing.Patterns;

import Display.Colour;
import Tuples.Point;

public class TestPattern extends ParentPattern {

    public TestPattern()
    {

    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    public Colour localColourAt(Point localPoint)
    {
        return new Colour(localPoint.x, localPoint.y, localPoint.z);
    }
}
