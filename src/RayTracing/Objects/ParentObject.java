package RayTracing.Objects;

import Matrices.Matrix;
import RayTracing.Intersections;
import RayTracing.Material;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Tuple;
import Tuples.Vector;

public abstract class ParentObject {
    public Matrix transform;
    public Material material;
    public Vector normalAt(Point worldPoint)
    {
        Point objectPoint = new Point(transform.inverse().times(worldPoint));
        Vector objectNormal = localNormalAt(objectPoint);
        Tuple worldNormal = transform.inverse().transposed().times(objectNormal);
        worldNormal.w = 0;
        return new Vector(worldNormal).normalised();
    }

    public Intersections intersections(Ray ray)
    {
        ray = ray.transform(transform.inverse());
        return localIntersections(ray);
    }

    public abstract Vector localNormalAt(Point localPoint);

    public abstract Intersections localIntersections(Ray localRay);

}
