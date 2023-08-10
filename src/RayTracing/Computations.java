package RayTracing;

import RayTracing.Objects.ParentObject;
import Tuples.Point;
import Tuples.Vector;

public class Computations {
    public double time;
    public ParentObject object;
    public Point point;
    public Point overPoint; // This point is just above the surface with point, and is used to for shadowing and reflection purposes.
    public Point underPoint; // This point is just below the surface with point, and is used for refraction purposes.
    public Vector eyeV;
    public Vector normalV;
    public Vector reflectV;
    public boolean inside; // Whether the ray meets the surface inside the object or not.
    public double nOne;
    public double nTwo;
    public Computations(double t, ParentObject obj, Point p, Vector eV, Vector nV, Vector rV, double nO, double nT)
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
        underPoint = new Point(point.minus(normalV.scalarMultiply(0.00001)));
        reflectV = rV;
        nOne = nO;
        nTwo = nT;
    }

    public double schlick()
    // https://graphics.stanford.edu/courses/cs148-10-summer/docs/2006--degreve--reflection_refraction.pdf
    // for calculating the amount of light which is reflected/refracted on incidence with a surface which is both
    // reflective and transparent. This is an approximation of the Fresnel factor.
    {
        double cos = eyeV.dot(normalV);
        if (nOne > nTwo) // If TIR can occur.
        {
            double nRatio = nOne / nTwo;
            double sinTSquared = Math.pow(nRatio, 2) * (1 - Math.pow(cos, 2));
            if (sinTSquared > 1) return 1; // 100% of rays are reflected if TIR occurs.
            cos = Math.sqrt(1 - sinTSquared);
        }
        double rZero = Math.pow((nOne - nTwo)/(nOne + nTwo), 2);
        return rZero + ((1 - rZero) * Math.pow(1 - cos, 5));
    }
}
