public class Vector extends Tuple {
    public Vector(double X, double Y, double Z)
    {
        super(X, Y, Z, 0);
    }

    public Vector negate()
    {
        return new Vector(-x, -y, -z);
    }
}