package RayTracing.Objects;

import Matrices.Matrix;
import RayTracing.Intersections;
import RayTracing.Material;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Vector;

public interface RayTracerObject {
    Intersections intersections(Ray ray); // Ensure the ray is transformed by the inverse of the Object transform, then
                                          // calculate the intersections with the unit object.
    void setTransform(Matrix m);
    Matrix getTransform();
    void setMaterial(Material mat);
    Material getMaterial();
    Vector normalAt(Point worldPoint); // Ensure that the worldPoint is transformed by the inverse of the Object
                                       // transform, then the local normal is found, and it is then multiplied by the
                                       // transposed inverse of the Object transform to find the worldNormal. Ensure
                                       // that worldNormal.w is set to = 0, as translations will change this value.
}
