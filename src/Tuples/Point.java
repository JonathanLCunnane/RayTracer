package Tuples;

public class Point extends Tuple {
    public Point(double X, double Y, double Z)
    {
        super(X, Y, Z, 1);
    }

    public Point(Tuple t)
    {
        super(t.x, t.y, t.z, t.w);
        if (!t.isPoint()) throw new IllegalArgumentException("A tuple that is not a point cannot be passed into the Tuples.Point constructor.");
    }
}