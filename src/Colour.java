public class Colour {
    double red;
    double green;
    double blue;
    public Colour() // Default colour is black. // Values of r, g, and b should be between 0 and 1.
    {
        red = 0;
        green = 0;
        blue = 0;
    }
    public Colour(double Red, double Green, double Blue)
    {
        red = Red;
        green = Green;
        blue = Blue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Colour c)) return false;
        return Math.abs(c.red - red) <= 0.00001
                && Math.abs(c.green - green) <= 0.00001
                && Math.abs(c.blue - blue) <= 0.00001;
    }

    public Colour plus(Colour c)
    {
        return new Colour(
                red + c.red,
                green + c.green,
                blue + c.blue
                );
    }

    public Colour minus(Colour c)
    {
        return new Colour(
                red - c.red,
                green - c.green,
                blue - c.blue
        );
    }

    public Colour times(Colour c) // This is the Hadamard/Schur product.
    {
        return new Colour(
                red * c.red,
                green * c.green,
                blue * c.blue
        );
    }

    public Colour scalarMultiply(double scalar)
    {
        return new Colour(
                red * scalar,
                green * scalar,
                blue * scalar
        );
    }

    public Colour scalarDivide(double scalar)
    {
        return new Colour(
                red / scalar,
                green / scalar,
                blue / scalar
        );
    }
}
