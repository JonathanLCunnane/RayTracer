package RayTracing;

import RayTracing.Objects.RayTracerObject;
import Tuples.Point;
import Tuples.Vector;

public class Computations {
    public double time;
    public RayTracerObject object;
    public Point point;
    public Point overPoint; // This point is just above the surface with point, and is used to for shadowing purposes.
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
        overPoint = new Point(point.plus(normalV.scalarMultiply(0.00001)));
    }
}
