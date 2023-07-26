public class Ray {
    public Point origin;
    public Vector direction;
    public Ray(Point Origin, Vector Direction)
    {
        origin = Origin;
        direction = Direction;
    }

    public Tuple position(double time)
    {
        return origin.plus(direction.scalarMultiply(time));
    }

    public Ray transform(Matrix m)
    {
        return new Ray(
                new Point(m.times(origin)),
                new Vector(m.times(direction))
        );
    }
}
