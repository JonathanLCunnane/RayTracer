import Display.Camera;
import Display.Canvas;
import Display.Colour;
import Matrices.*;
import RayTracing.Material;
import RayTracing.Objects.ParentObject;
import RayTracing.Objects.Plane;
import RayTracing.Objects.Sphere;
import RayTracing.PointLight;
import RayTracing.World;
import Tuples.Point;
import Tuples.Vector;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SecondWorldRender {
    public static void main(String[] args) throws IOException
    {
        World w = new World();

        // Configure the floor as a plane.
        Material wallMaterial = new Material(
                new Colour(0.95, 0.9, 0.9),
                0.1, 0.9, 0, 200, 0 // The walls will be matte hence why sP = 0
        );
        Plane floor = new Plane();
        floor.transform = new ScalingMatrix(10, 0.01, 10);
        floor.material = wallMaterial;

        // Configure the 3 spheres in the image.
        Sphere left = new Sphere();
        left.transform =
                new TranslationMatrix(-2, 0.33333, -0.75).times(
                new ScalingMatrix(0.33333, 0.33333, 0.33333));
        left.material =
                new Material(
                        new Colour(0.18, 0.702, 0.722),
                        0.1, 0.7, 0.3, 200, 0
                );
        Sphere middle = new Sphere();
        middle.transform = new TranslationMatrix(-0.5, 1, 0.5);
        middle.material =
                new Material(
                        new Colour(0.8, 0.263, 0.463),
                        0.1, 0.7, 0.3, 200, 0
                );
        Sphere right = new Sphere();
        right.transform =
                new TranslationMatrix(1.5, 0.5, -0.5).times(
                new ScalingMatrix(1, 0.5, 1));
        right.material =
                new Material(
                        new Colour(0.141, 0.302, 0.78),
                        0.1, 0.5, 0.2, 10, 0
                );

        w.objects = new ParentObject[]
                {
                        floor,
                        left,
                        middle,
                        right
                };
        w.light = new PointLight(
                new Point(-10, 10, -10),
                new Colour(0.988, 0.855, 0.941)
        );

        // Setup camera and render image
        Camera c = new Camera(1920, 1080, 2 * (Math.PI/3));
        c.transform = new OrientationMatrix(
                new Point(0, 2, -5),
                new Point(0, 1, 0),
                new Vector(0, 1, 0)
        ).viewTransform();

        long startTime = System.currentTimeMillis();
        Canvas canvas = c.renderCanvas(w);
        long elapsedTimeInSeconds = (System.currentTimeMillis() - startTime) / 1000;
        System.out.printf("Took %ds to render.%n", elapsedTimeInSeconds);

        String out = canvas.toPlainPPM();
        Path path = Paths.get(".\\secondworldrender.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
        System.out.println("Done.");
    }
}
