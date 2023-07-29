import Display.Camera;
import Display.Canvas;
import Display.Colour;
import Matrices.*;
import RayTracing.Material;
import RayTracing.Objects.RayTracerObject;
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
                0.1, 0.9, 0, 200 // The walls will be matte hence why sP = 0
        );
        Sphere floor = new Sphere();
        floor.setTransform(new ScalingMatrix(10, 0.01, 10));
        floor.setMaterial(wallMaterial);
        Sphere leftWall = new Sphere();
        leftWall.setTransform(
                new TranslationMatrix(0, 0, 5).times(
                new YRotationMatrix(-Math.PI/4).times(
                new XRotationMatrix(Math.PI/2).times(
                new ScalingMatrix(10, 0.01, 10))))
        );
        leftWall.setMaterial(wallMaterial);
        Sphere rightWall = new Sphere();
        rightWall.setTransform(
                new TranslationMatrix(0, 0, 5).times(
                new YRotationMatrix(Math.PI/4).times(
                new XRotationMatrix(Math.PI/2).times(
                new ScalingMatrix(10, 0.01, 10))))
        );
        rightWall.setMaterial(wallMaterial);

        // Configure the 3 spheres in the image.
        Sphere left = new Sphere();
        left.setTransform(
                new TranslationMatrix(-1.5, 0.33333, -0.75).times(
                new ScalingMatrix(0.33333, 0.33333, 0.33333))
        );
        left.setMaterial(
                new Material(
                        new Colour(1, 0.8, 0.1),
                        0.1, 0.7, 0.3, 200
                )
        );
        Sphere middle = new Sphere();
        middle.setTransform(new TranslationMatrix(-0.5, 1, 0.5));
        middle.setMaterial(
                new Material(
                        new Colour(0.1, 1, 0.5),
                        0.1, 0.7, 0.3, 200
                )
        );
        Sphere right = new Sphere();
        right.setTransform(
                new TranslationMatrix(1.5, 0.5, -0.5).times(
                new ScalingMatrix(1, 0.5, 1))
        );
        right.setMaterial(
                new Material(
                        new Colour(0.8, 0.2, 0.3),
                        0.1, 0.7, 0.3, 200
                )
        );

        w.objects = new RayTracerObject[]
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
