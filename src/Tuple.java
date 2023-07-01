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
        return Double.compare(t.x, x) == 0
            && Double.compare(t.y, y) == 0
            && Double.compare(t.z, z) == 0
            && Double.compare(t.w, w) == 0;
    }
}
