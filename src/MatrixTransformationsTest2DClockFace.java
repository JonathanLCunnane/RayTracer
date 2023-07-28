import Display.Canvas;
import Display.Colour;
import Matrices.Matrix;
import Matrices.ZRotationMatrix;
import Tuples.Point;
import Tuples.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class MatrixTransformationsTest2DClockFace {
    public static void main(String[] args) throws IOException {
        Colour WHITE = new Colour(255, 255, 255);
        Canvas c = new Canvas(50, 50); // Let the origin be at the pixel (25, 25).
        // Let the canvas be the plane of z=0.
        Tuple p = new Point(20, 0, 0);
        Matrix twelfth = new ZRotationMatrix(Math.PI/6);
        for (int i = 0; i < 12; i++)
        {
            // Draw Tuples.Point p on canvas.
            c.writeToCanvas(WHITE, (int) Math.round(p.x) + 25, (int) Math.round(p.y) + 25);
            // Rotate Tuples.Point p.
            p = twelfth.times(p);
        }
        String out = c.toPlainPPM();
        Path path = Paths.get(".\\clockface.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
    }
}
