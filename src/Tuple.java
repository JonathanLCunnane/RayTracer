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
}
