import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SphereSilhouette {
    public static void main(String[] args) throws IOException {
        Sphere s = new Sphere();
        Matrix m = new ShearingMatrix(0.5, 0.7, 0.1, 0.3, 1.1, 1);
        s.setTransform(m);
        Point rayOrigin = new Point(0, 0, -5);
        double wallZ = 10;
        double wallSize = 20;
        double halfWallSize = wallSize / 2;
        int canvasSize = 256;
        double pixelWidth = wallSize / canvasSize;
        Canvas c = new Canvas(canvasSize, canvasSize);
        Colour cyan = new Colour(0, 100, 100);
        for (int canvasY = 0; canvasY < canvasSize; canvasY++)
        {
            double rayDestinationOnWallY = halfWallSize - canvasY * pixelWidth;
            for (int canvasX = 0; canvasX < canvasSize; canvasX++)
            {
                double rayDestinationOnWallX = canvasX * pixelWidth - halfWallSize;
                Point rayDestinationOnWall = new Point(rayDestinationOnWallX, rayDestinationOnWallY, wallZ);
                Ray r = new Ray(
                        rayOrigin,
                        new Vector(rayDestinationOnWall.minus(rayOrigin))
                );
                Intersections xs = s.intersections(r);
                if (xs.hit() != null) c.writeToCanvas(cyan, canvasX, canvasY);
            }
        }

        String out = c.toPlainPPM();
        Path path = Paths.get(".\\spheresilhouette.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
    }
}
