package Tuples;

public class Tuple {
    public double x;
    public double y;
    public double z;
    public double w;

    public Tuple(double X, double Y, double Z, double W)
    {
        x = X;
        y = Y;
        z = Z;
        w = W;
    }

    public boolean isPoint()
    {
        return w == 1;
    }

    public boolean isVector()
    {
        return w == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Tuple t)) return false;
        return Math.abs(t.x - x) <= 0.00001
            && Math.abs(t.y - y) <= 0.00001
            && Math.abs(t.z - z) <= 0.00001
            && Math.abs(t.w - w) <= 0.00001;
    }

    public Tuple plus(Tuple obj)
    {
        if (obj == null) return null;
        Tuple newT = new Tuple(x + obj.x, y + obj.y, z + obj.z, w + obj.w);
        if (!(Double.compare(newT.w, 0) == 0 || Double.compare(newT.w, 1) == 0)) throw new IllegalArgumentException(
                "Only a Tuples.Vector can be added to a Tuples.Point and a Tuples.Vector to a Tuples.Vector."
        );
        return newT;
    }

    public Tuple minus(Tuple obj)
    {
        if (obj == null) return null;
        Tuple newT = new Tuple(x - obj.x, y - obj.y, z - obj.z, w - obj.w);
        if (!(Double.compare(newT.w, 0) == 0 || Double.compare(newT.w, 1) == 0)) throw new IllegalArgumentException(
                "Only a Tuples.Vector can be subtracted from a Tuples.Point and a Tuples.Vector from a Tuples.Vector."
        );
        return newT;
    }
}
