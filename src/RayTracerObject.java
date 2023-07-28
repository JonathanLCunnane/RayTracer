public interface RayTracerObject {
    Intersections intersections(Ray ray);
    void setTransform(Matrix m);
    Matrix getTransform();
    void setMaterial(Material mat);
    Material getMaterial();
    Vector normalAt(Point worldPoint);
}
