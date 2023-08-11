package RayTracing.Objects;

import RayTracing.Intersection;
import RayTracing.Intersections;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Vector;

import java.util.Objects;

public class Cube extends ParentObject{ // The default cube is 2x2x2 centered around the origin.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube c = (Cube) o;
        if (!Objects.equals(c.material, material)) return false;
        return Objects.equals(c.transform, transform);
    }

    @Override
    public Vector localNormalAt(Point localPoint) {
        double absMax = Math.max(Math.max(Math.abs(localPoint.x), Math.abs(localPoint.y)), Math.abs(localPoint.z));
        if (absMax == Math.abs(localPoint.x)) return new Vector(localPoint.x, 0, 0);
        else if (absMax == Math.abs(localPoint.y)) return new Vector(0, localPoint.y, 0);
        return new Vector(0, 0, localPoint.z);
    }

    @Override
    public Intersections localIntersections(Ray localRay) {
        // We consider where the ray intersects the xy, xz, and yz planes.
        double[] xTimes = rayPlaneIntersectTimes(localRay.origin.x, localRay.direction.x);
        double[] yTimes = rayPlaneIntersectTimes(localRay.origin.y, localRay.direction.y);
        double[] zTimes = rayPlaneIntersectTimes(localRay.origin.z, localRay.direction.z);
        double tMin = Math.max(Math.max(xTimes[0], yTimes[0]), zTimes[0]);
        double tMax = Math.min(Math.min(xTimes[1], yTimes[1]), zTimes[1]);
        if (tMin > tMax) return new Intersections(new Intersection[] {});
        return new Intersections(
                new Intersection[]
                        {
                                new Intersection(this, tMin),
                                new Intersection(this, tMax),
                        }
        );
    }

    private double[] rayPlaneIntersectTimes(double rayOriginInAxis, double rayDirectionInAxis)
    {
        double tMinNum = -1 - rayOriginInAxis;
        double tMaxNum = 1 - rayOriginInAxis;
        double tMin;
        double tMax;
        if (Math.abs(rayDirectionInAxis) >= 0.00001)
        {
            tMin = tMinNum / rayDirectionInAxis;
            tMax = tMaxNum / rayDirectionInAxis;
        }
        else
        {
            tMin = tMinNum * Double.POSITIVE_INFINITY;
            tMax = tMaxNum * Double.POSITIVE_INFINITY;
        }
        if (tMin > tMax) return new double[] {tMax, tMin};
        return new double[] {tMin, tMax};
    }
}
