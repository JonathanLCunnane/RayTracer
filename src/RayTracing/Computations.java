package RayTracing;

import RayTracing.Objects.RayTracerObject;
import Tuples.Point;
import Tuples.Vector;

public class Computations {
    public double time;
    public RayTracerObject object;
    public Point point;
    public Vector eyeV;
    public Vector normalV;
    public boolean inside; // Whether the ray meets the surface inside the object or not.
    public Computations(double t, RayTracerObject obj, Point p, Vector eV, Vector nV)
    {
        time = t;
        object = obj;
        point = p;
        eyeV = eV;
        if (nV.dot(eV) < 0)
        {
            normalV = nV.scalarMultiply(-1);
            inside = true;
        }
        else
        {
            normalV = nV;
            inside = false;
        }
    }
}
