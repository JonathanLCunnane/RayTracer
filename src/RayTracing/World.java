package RayTracing;

import Display.Colour;
import RayTracing.Objects.RayTracerObject;

import java.util.Objects;

public class World {
    public RayTracerObject[] objects;
    public PointLight light;

    public World()
    {
        objects = new RayTracerObject[] {};
        light = null;
    }

    public World(RayTracerObject[] objs, PointLight lightSource)
    {
        objects = objs;
        light = lightSource;
    }

    public boolean contains(RayTracerObject object)
    {
        for (RayTracerObject possibleObject : objects)
        {
            if (Objects.equals(possibleObject, object)) return true;
        }
        return false;
    }

    public Intersections intersections(Ray r)
    {
        Intersections xs = new Intersections(new Intersection[] {});
        for (RayTracerObject object : objects)
        {
            xs = xs.combine(object.intersections(r));
        }
        return xs;
    }

    public Colour shadeHit(Computations c)
    {
        return c.object.getMaterial().lightningAtPoint(
                light,
                c.point,
                c.eyeV,
                c.normalV
        );
    }

    public Colour colourAt(Ray r)
    {
        Intersections xs = intersections(r);
        Intersection i = xs.hit();
        if (i == null) return new Colour(0, 0, 0); // Black is returned if there is no object intersection
        Computations c = i.prepareComputations(r);
        return shadeHit(c);
    }
}
