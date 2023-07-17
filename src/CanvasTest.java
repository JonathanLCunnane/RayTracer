import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CanvasTest {
    @Test
    @DisplayName("Creating a canvas, and checking all pixels are black and the dimensions are correct")
    public void creatingCanvasTest()
    {
        Canvas c = new Canvas(5, 10);
        Assertions.assertAll(
                () -> Assertions.assertEquals(c.width, 5),
                () -> Assertions.assertEquals(c.height, 10)
        );
        Colour black = new Colour();
        for (int x = 0; x < c.width; x++)
        {
            for (int y = 0; y < c.height; y++)
            {
                Assertions.assertEquals(c.pixelAt(x, y), black);
            }
        }
    }

    @Test
    @DisplayName("Writing pixels to a canvas")
    public void writingToCanvasTest()
    {
        Canvas c = new Canvas(5, 10);
        Colour r = new Colour(1, 0, 0);
        c.writeToCanvas(r, 2, 3);
        Assertions.assertEquals(c.pixelAt(2, 3), r);
    }

    @Test
    @DisplayName("Constructing plain PPM header")
    public void PPMHeaderTest()
    {
        Canvas c = new Canvas(5, 3);
        String ppm = c.toPlainPPM();
        Assertions.assertTrue(
                ppm.startsWith(
                        """
                        P3
                        # Created from JLC's canvas toPlainPPM method.
                        5 3
                        255
                        """
                )
        );
    }

    @Test
    @DisplayName("Constructing plain PPM pixel data")
    public void PPMPixelDataTest()
    {
        Canvas c = new Canvas(5, 3);
        Colour cOne = new Colour(1.5, 0, 0);
        Colour cTwo = new Colour(0, 0.5, 0);
        Colour cThree = new Colour(-0.5, 0, 1);
        c.writeToCanvas(cOne, 0, 0);
        c.writeToCanvas(cTwo, 2, 1);
        c.writeToCanvas(cThree, 4, 2);
        String ppm = c.toPlainPPM();
        Assertions.assertTrue(
                ppm.endsWith(
                        """
                        255 0 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 128 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 0 0
                        0 0 255
                        """
                )
        );
    }
}
