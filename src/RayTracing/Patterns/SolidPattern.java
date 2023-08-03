package RayTracing.Patterns;

import Display.Colour;
import Tuples.Point;

import java.util.Objects;

public class SolidPattern extends ParentPattern {
    public Colour colour;
    public SolidPattern(Colour c)
    {
        colour = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolidPattern that = (SolidPattern) o;
        return Objects.equals(colour, that.colour);
    }

    public Colour localColourAt(Point localPoint)
    {
        return colour;
    }
}
