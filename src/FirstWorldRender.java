import Display.Camera;
import Display.Canvas;
import Display.Colour;
import Matrices.*;
import RayTracing.Material;
import RayTracing.Objects.ParentObject;
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

public class FirstWorldRender {
    // This render will use very squashes spheres for walls, as I have not yet implemented planes or cubes.
    public static void main(String[] args) throws IOException
    {
        World w = new World();

        // Configure the 3 walls in the image.
        Material wallMaterial = new Material(
                new Colour(0.95, 0.9, 0.9),
                0.1, 0.9, 0, 200, 0 // The walls will be matte hence why sP = 0
        );
        Sphere floor = new Sphere();
        floor.transform = new ScalingMatrix(10, 0.01, 10);
        floor.material = wallMaterial;
        Sphere leftWall = new Sphere();
        leftWall.transform =
                new TranslationMatrix(0, 0, 5).times(
                new YRotationMatrix(-Math.PI/4).times(
                new XRotationMatrix(Math.PI/2).times(
                new ScalingMatrix(10, 0.01, 10))));
        leftWall.material = wallMaterial;
        Sphere rightWall = new Sphere();
        rightWall.transform =
                new TranslationMatrix(0, 0, 5).times(
                new YRotationMatrix(Math.PI/4).times(
                new XRotationMatrix(Math.PI/2).times(
                new ScalingMatrix(10, 0.01, 10))));
        rightWall.material = wallMaterial;

        // Configure the 3 spheres in the image.
        Sphere left = new Sphere();
        left.transform =
                new TranslationMatrix(-1.5, 0.33333, -0.75).times(
                new ScalingMatrix(0.33333, 0.33333, 0.33333));
        left.material =
                new Material(
                        new Colour(1, 0.8, 0.1),
                        0.1, 0.7, 0.3, 200, 0
                );
        Sphere middle = new Sphere();
        middle.transform = new TranslationMatrix(-0.5, 1, 0.5);
        middle.material =
                new Material(
                        new Colour(0.1, 1, 0.5),
                        0.1, 0.7, 0.3, 200, 0
                );
        Sphere right = new Sphere();
        right.transform =
                new TranslationMatrix(1.5, 0.5, -0.5).times(
                new ScalingMatrix(1, 0.5, 1));
        right.material =
                new Material(
                        new Colour(0.8, 0.2, 0.3),
                        0.1, 0.7, 0.3, 200, 0
                );

        w.objects = new ParentObject[]
                {
                        floor,
                        leftWall,
                        rightWall,
                        left,
                        middle,
                        right
                };
        w.light = new PointLight(
                new Point(-10, 10, -10),
                new Colour(1, 1, 1)
        );

        // Setup camera and render image
        Camera c = new Camera(1920, 1080, Math.PI/3);
        c.transform = new OrientationMatrix(
                new Point(0, 1.5, -5),
                new Point(0, 1, 0),
                new Vector(0, 1, 0)
        ).viewTransform();
        Canvas canvas = c.renderCanvas(w);
        String out = canvas.toPlainPPM();
        Path path = Paths.get(".\\firstworldrender.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
    }
}
