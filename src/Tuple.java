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

    public Tuple plus(Tuple obj)
    {
        if (obj == null) return null;
        Tuple newT = new Tuple(x + obj.x, y + obj.y, z + obj.z, w + obj.w);
        if (!(Double.compare(newT.w, 0) == 0 || Double.compare(newT.w, 1) == 0)) throw new IllegalArgumentException(
                "Only a Vector can be added to a Point and a Vector to a Vector."
        );
        return newT;
    }

    public Tuple minus(Tuple obj)
    {
        if (obj == null) return null;
        Tuple newT = new Tuple(x - obj.x, y - obj.y, z - obj.z, w - obj.w);
        if (!(Double.compare(newT.w, 0) == 0 || Double.compare(newT.w, 1) == 0)) throw new IllegalArgumentException(
                "Only a Vector can be subtracted from a Point and a Vector from a Vector."
        );
        return newT;
    }
}
