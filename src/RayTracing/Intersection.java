package RayTracing;

import RayTracing.Objects.ParentObject;
import Tuples.Point;
import Tuples.Vector;

import java.util.Objects;

public class Intersection {
    public ParentObject object;
    public double time;
    public Intersection(ParentObject obj, double t)
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

    public Computations prepareComputations(Ray r)
    {
        Point p = new Point(r.position(time));
        Vector nV = object.normalAt(p); // Normal vector
        Vector eV = r.direction.normalised().scalarMultiply(-1); // Eye vector
        return new Computations(time, object, p, eV, nV);
    }
}
