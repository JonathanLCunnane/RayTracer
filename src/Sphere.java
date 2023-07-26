public class Sphere {
    public Point c;
    public double r;
    public Matrix transform;
    public Sphere()
    {
        c = new Point(0, 0,0);
        r = 1;
        transform = new IdentityMatrix(4);
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
}
