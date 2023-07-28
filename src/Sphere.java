public class Sphere implements RayTracerObject {
    public Point c;
    public double r;
    private Matrix transform;
    private Material material;
    public Sphere()
    {
        c = new Point(0, 0,0);
        r = 1;
        transform = new IdentityMatrix(4);
        material = new Material();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere s = (Sphere) o;
        if (s.c != c) return false;
        return s.r == r;
    }

    public Intersections intersections(Ray ray)
    {
        ray = ray.transform(transform.inverse()); // We will transform the ray with the inverse transformation of the
                                                  // sphere, instead of transforming the sphere, as this is easier.
        // From https://en.wikipedia.org/wiki/Line–sphere_intersection
        Point o = ray.origin;
        Vector u = ray.direction;
        Vector omc = new Vector(o.minus(c));
        double a = u.dot(u);
        double b = 2 * u.dot(omc);
        double c = omc.dot(omc) - Math.pow(r, 2);
        double delta = Math.pow(b, 2) - (4 * a * c);
        if (delta < 0) return new Intersections(new Intersection[0]);
        double tOne = (- b - Math.pow(delta, 0.5)) / (2 * a);
        double tTwo = (- b + Math.pow(delta, 0.5)) / (2 * a);
        Intersection[] xs = new Intersection[]
                {
                        new Intersection(this, tOne),
                        new Intersection(this, tTwo)
                };
        return new Intersections(xs);
    }

    public void setTransform(Matrix m)
    {
        transform = m;
    }

    public Matrix getTransform()
    {
        return transform;
    }

    public void setMaterial(Material mat)
    {
        material = mat;
    }

    public Material getMaterial()
    {
        return material;
    }

    public Vector normalAt(Point worldPoint)
    {
        // We are going to change the worldPoint to an object-relative point objectPoint, find the normal at the
        // object point, then transform the normal to the world-relative normal to be returned.
        // Note that the w value of the normal after being multiplied by the inverse transpose has to be manually set
        // to zero due to any translation in the sphere's transformation altering the w value unnecessarily.
        //
        Point objectPoint = new Point(transform.inverse().times(worldPoint));
        Vector objectNormal = new Vector(objectPoint.minus(new Point(0, 0, 0)));
        Tuple worldNormal = transform.inverse().transposed().times(objectNormal);
        worldNormal.w = 0;
        return new Vector(worldNormal).normalised();
    }
}
