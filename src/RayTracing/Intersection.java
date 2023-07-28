package RayTracing;

import RayTracing.Objects.RayTracerObject;

import java.util.Objects;

public class Intersection {
    public RayTracerObject object;
    public double time;
    public Intersection(RayTracerObject obj, double t)
    {
        object = obj;
        time = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection i = (Intersection) o;
        if (!Objects.equals(i.object, object)) return false;
        return i.time == time;
    }
}
