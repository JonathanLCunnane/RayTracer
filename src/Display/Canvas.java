package Display;

public class Canvas {
    private Colour[][] canvas;
    int width;
    int height;
    public Canvas(int Width, int Height)
    {
        width = Width;
        height = Height;
        initialiseCanvas();
    }

    private void initialiseCanvas()
    {
        canvas = new Colour[width][height];
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                canvas[x][y] = new Colour();
            }
        }
    }

    private long lerp(double d)
    {
        long out = Math.round(255 * d);
        if (out < 0) return 0;
        if (out > 255) return 255;
        return out;
    }

    public void writeToCanvas(Colour c, int x, int y)
    {
        if ((x < 0) || (x >= width)) throw new IllegalArgumentException("'x' must be between 0 and width - 1.");
        if ((y < 0) || (y >= height)) throw new IllegalArgumentException("'y' must be between 0 and height - 1.");
        canvas[x][y] = c;
    }

    public Colour pixelAt(int x, int y)
    {
        if ((x < 0) || (x >= width)) throw new IllegalArgumentException("'x' must be between 0 and width - 1.");
        if ((y < 0) || (y >= height)) throw new IllegalArgumentException("'y' must be between 0 and height - 1.");
        return canvas[x][y];
    }

    public String toPlainPPM()
    {
        String header =
                String.format("""
                        P3
                        # Created from JLC's canvas toPlainPPM method.
                        %d %d
                        255
                        """,
                        width, height);
        StringBuilder out = new StringBuilder(header);
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                out.append(String.format("%d %d %d", lerp(canvas[x][y].red), lerp(canvas[x][y].green), lerp(canvas[x][y].blue)));
                out.append("\n");
            }
        }
        return out.toString();
    }
}
