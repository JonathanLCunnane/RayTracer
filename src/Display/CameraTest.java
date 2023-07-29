package Display;

import Matrices.IdentityMatrix;
import Matrices.OrientationMatrix;
import Matrices.TranslationMatrix;
import Matrices.YRotationMatrix;
import RayTracing.DefaultWorld;
import RayTracing.Ray;
import RayTracing.World;
import Tuples.Point;
import Tuples.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CameraTest {
    @Test
    @DisplayName("Constructing a camera")
    public void cameraConstruction()
    {
        Camera c = new Camera(160, 120, Math.PI/2);
        Assertions.assertAll(
                () -> Assertions.assertEquals(c.hSize, 160),
                () -> Assertions.assertEquals(c.vSize, 120),
                () -> Assertions.assertEquals(c.fOV, Math.PI/2),
                () -> Assertions.assertEquals(c.transform, new IdentityMatrix(4))
        );
    }

    @Test
    @DisplayName("Pixel size is correctly calculated if hSize > vSize")
    public void largerHSize()
    {
        Camera c = new Camera(200, 125, Math.PI/2);
        Assertions.assertFalse((c.pixelSize - 0.01) > 0.00001 || (0.01 - c.pixelSize) > 0.00001);
    }

    @Test
    @DisplayName("Pixel size is correctly calculated if vSize > hSize")
    public void largerVSize()
    {
        Camera c = new Camera(125, 200, Math.PI/2);
        Assertions.assertFalse((c.pixelSize - 0.01) > 0.00001 || (0.01 - c.pixelSize) > 0.00001);
    }

    @Test
    @DisplayName("Ray through the center of the canvas")
    public void centerRay()
    {
        Camera c = new Camera(201, 101, Math.PI/2);
        Ray r = c.rayForPixel(100, 50);
        Assertions.assertAll(
                () -> Assertions.assertEquals(r.origin, new Point(0, 0, 0)),
                () -> Assertions.assertEquals(r.direction, new Vector(0, 0, -1))
        );
    }

    @Test
    @DisplayName("Ray through the corner of the canvas")
    public void cornerRay()
    {
        Camera c = new Camera(201, 101, Math.PI/2);
        Ray r = c.rayForPixel(0, 0);
        Assertions.assertAll(
                () -> Assertions.assertEquals(r.origin, new Point(0, 0, 0)),
                () -> Assertions.assertEquals(r.direction, new Vector(0.66519, 0.33259, -0.66851))
        );
    }

    @Test
    @DisplayName("Ray when the camera is transformed")
    public void cameraTransformRay()
    {
        Camera c = new Camera(201, 101, Math.PI/2);
        c.transform = new YRotationMatrix(Math.PI/4).times(new TranslationMatrix(0, -2, 5));
        Ray r = c.rayForPixel(100, 50);
        Assertions.assertAll(
                () -> Assertions.assertEquals(r.origin, new Point(0, 2, -5)),
                () -> Assertions.assertEquals(r.direction, new Vector(Math.sqrt(2)/2, 0, -Math.sqrt(2)/2))
        );
    }

    @Test
    @DisplayName("Rendering a world with the camera")
    public void worldRender()
    {
        World w = new DefaultWorld();
        Camera c = new Camera(11, 11, Math.PI/2);
        Point from = new Point(0, 0, -5);
        Point to = new Point(0, 0, 0);
        Vector up = new Vector(0, 1, 0);
        c.transform = new OrientationMatrix(from, to, up).viewTransform();
        Canvas img = c.renderCanvas(w);
        Assertions.assertEquals(img.pixelAt(5, 5), new Colour(0.38066, 0.47583, 0.2855));
    }
}
