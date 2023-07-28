package RayTracing;

import Display.Colour;
import Tuples.Point;

public class PointLight {
    Point position;
    Colour intensity;
    public PointLight(Point pos, Colour _intensity)
    {
        position = pos;
        intensity = _intensity;
    }
}
