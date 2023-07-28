package RayTracing;

import Display.Colour;
import Tuples.Point;

import java.util.Objects;

public class PointLight {
    Point position;
    Colour intensity;
    public PointLight(Point pos, Colour _intensity)
    {
        position = pos;
        intensity = _intensity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointLight that = (PointLight) o;
        return Objects.equals(position, that.position) && Objects.equals(intensity, that.intensity);
    }
}
