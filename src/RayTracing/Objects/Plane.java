package RayTracing.Objects;

import Matrices.IdentityMatrix;
import RayTracing.Intersection;
import RayTracing.Intersections;
import RayTracing.Material;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Vector;

import java.util.Objects;

public class Plane extends ParentObject{

    public Plane()
    {
        transform = new IdentityMatrix(4);
        material = new Material();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane s = (Plane) o;
        if (!Objects.equals(s.material, material)) return false;
        return Objects.equals(s.transform, transform);
    }

    public Intersections localIntersections(Ray ray) {
        // If the ray is parallel to the plane then there are no intersections.
        if (Math.abs(ray.direction.y) < 0.00001) return new Intersections(new Intersection[] {});
        return new Intersections(
                new Intersection[] {new Intersection(
                        this,
                        - ray.origin.y / ray.direction.y // https://en.wikipedia.org/wiki/Line–plane_intersection
                )}
        );
    }

    public Vector localNormalAt(Point worldPoint) {
        return new Vector(0, 1, 0);
    }
}
