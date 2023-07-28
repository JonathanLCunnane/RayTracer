package RayTracing.Objects;

import Matrices.Matrix;
import RayTracing.Intersections;
import RayTracing.Material;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Vector;

public interface RayTracerObject {
    Intersections intersections(Ray ray);
    void setTransform(Matrix m);
    Matrix getTransform();
    void setMaterial(Material mat);
    Material getMaterial();
    Vector normalAt(Point worldPoint);
}
