package RayTracing;

import RayTracing.Objects.RayTracerObject;

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
        if (!i.object.equals(object)) return false;
        return i.time == time;
    }
}
