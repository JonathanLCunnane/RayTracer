package RayTracing;

import RayTracing.Objects.ParentObject;
import Tuples.Point;
import Tuples.Vector;

import java.util.ArrayList;
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

    public Computations prepareComputations(Ray r) // Computations where there is no refraction wanted.
    {
        Point p = new Point(r.position(time));
        Vector nV = object.normalAt(p); // Normal vector
        Vector eV = r.direction.normalised().scalarMultiply(-1); // Eye vector
        Vector rV = r.direction.reflect(nV);
        return new Computations(time, object, p, eV, nV, rV, 1, 1);
    }

    public Computations prepareRefractionComputations(Ray r, Intersections xs)
    {
        double nOne = 1;
        double nTwo = 1;
        boolean isThis = false;
        ArrayList<ParentObject> containers = new ArrayList<>();
        for (Intersection i : xs.intersections)
        {
            if (i.equals(this))
            {
                isThis = true;
                if (!containers.isEmpty())
                {
                    nOne = containers.get(containers.size() - 1).material.refractiveIndex;
                }
            }

            if (containers.contains(i.object))
            {
                containers.remove(i.object);
            }
            else
            {
                containers.add(i.object);
            }

            if (isThis)
            {
                if (!containers.isEmpty())
                {
                    nTwo = containers.get(containers.size() - 1).material.refractiveIndex;
                }
                break;
            }
        }
        Point p = new Point(r.position(time));
        Vector nV = object.normalAt(p); // Normal vector
        Vector eV = r.direction.normalised().scalarMultiply(-1); // Eye vector
        Vector rV = r.direction.reflect(nV);
        return new Computations(time, object, p, eV, nV, rV, nOne, nTwo);
    }
}
