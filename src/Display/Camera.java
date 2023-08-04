package Display;

import Matrices.IdentityMatrix;
import Matrices.Matrix;
import RayTracing.Ray;
import RayTracing.World;
import Tuples.Point;
import Tuples.Vector;

public class Camera {
    public int hSize;
    public int vSize;
    public double fOV;
    public Matrix transform;
    public double pixelSize;
    public double halfWidth;
    public double halfHeight;
    public Camera(int x, int y, double fieldOfView)
    {
        hSize = x;
        vSize = y;
        fOV = fieldOfView;
        transform = new IdentityMatrix(4);
        initialiseCamera();
    }

    private void initialiseCamera() // The FOV is taken on the larger side.
    {
        double aspect = (double) hSize / vSize;
        double halfView = Math.tan(fOV / 2);
        if (aspect >= 1) // The larger side is horizontal
        {
            halfWidth = halfView;
            halfHeight = halfView / aspect;
        }
        else
        {
            halfWidth = halfView * aspect;
            halfHeight = halfView;
        }
        pixelSize = (halfWidth * 2) / hSize; // Which is the same as (halfHeight * 2) / vSize
    }

    public Ray rayForPixel(int x, int y)
    {
        if (x < 0 || y < 0) throw new IllegalArgumentException("x and y must be zero or positive.");
        if (x >= hSize || y >= vSize) throw new IllegalArgumentException("x and y must be less than hSize and vSize.");
        Point from = new Point(0, 0, 0);
        from = new Point(transform.inverse().times(from)); // The camera transformation describes how the WORLD is moved relative to the camera, not the other way around, so the inverse is taken of the matrix.
        Point to = new Point(halfWidth - (x + 0.5) * pixelSize, halfHeight - (y + 0.5) * pixelSize, -1);
        to = new Point(transform.inverse().times(to));
        return new Ray(
                from,
                new Vector(to.minus(from)).normalised()
        );
    }

    public Canvas renderCanvas(World w)
    {
        Canvas img = new Canvas(hSize, vSize);
        for (int y = 0; y < vSize; y++)
        {
            for (int x = 0; x < hSize; x++)
            {
                img.writeToCanvas(
                        w.colourAt(rayForPixel(x, y), 5),
                        x, y
                );
            }
        }
        return img;
    }
}
