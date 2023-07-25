import java.lang.Math;

public class Vector extends Tuple {
    public Vector(double X, double Y, double Z)
    {
        super(X, Y, Z, 0);
    }

    public Vector negate()
    {
        return new Vector(-x, -y, -z);
    }

    public Vector scalarMultiply(double scalar)
    {
        return new Vector(x * scalar, y * scalar, z * scalar);
    }

    public Vector scalarDivide(double scalar)
    {
        return new Vector(x / scalar, y / scalar, z / scalar);
    }

    public double magnitude()
    {
        return Math.pow((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)), 0.5);
    }

    public Vector normalised()
    {
        double mag = this.magnitude();
        return this.scalarDivide(mag);
    }

    public double dot(Tuple v)
    {
        return (x * v.x + y * v.y + z * v.z);
    }

    public Vector cross(Vector v)
    {
        return new Vector(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
    }
}