package RayTracing;

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
}
