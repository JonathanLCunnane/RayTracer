package RayTracing;

import Display.Colour;
import RayTracing.Objects.ParentObject;
import Tuples.Point;
import Tuples.Vector;

import java.util.Objects;

public class World {
    public ParentObject[] objects;
    public PointLight light;

    public World()
    {
        objects = new ParentObject[] {};
        light = null;
    }

    public World(ParentObject[] objs, PointLight lightSource)
    {
        objects = objs;
        light = lightSource;
    }

    public boolean contains(ParentObject object)
    {
        for (ParentObject possibleObject : objects)
        {
            if (Objects.equals(possibleObject, object)) return true;
        }
        return false;
    }

    public Intersections intersections(Ray r)
    {
        Intersections xs = new Intersections(new Intersection[] {});
        for (ParentObject object : objects)
        {
            xs = xs.combine(object.intersections(r));
        }
        return xs;
    }

    public Colour shadeHit(Computations c, int remainingIterations)
    {
        boolean inShadow = isInShadow(c.overPoint);
        Colour surfaceColour = c.object.material.lightningAtPoint(
                light,
                c.point,
                c.eyeV,
                c.normalV,
                inShadow,
                c.object
        );
        Colour reflectedColour = reflectedColour(c, remainingIterations);
        return surfaceColour.plus(reflectedColour);
    }

    public Colour colourAt(Ray r, int remainingIterations)
    {
        Intersections xs = intersections(r);
        Intersection i = xs.hit();
        if (i == null) return new Colour(0, 0, 0); // Black is returned if there is no object intersection
        Computations c = i.prepareComputations(r);
        return shadeHit(c, remainingIterations);
    }

    public Colour reflectedColour(Computations c, int remainingIterations)
    {
        if (remainingIterations <= 0) return new Colour(0, 0, 0);
        if (c.object.material.reflectiveness == 0) return new Colour(0, 0, 0);
        Ray reflectedRay = new Ray(
                c.overPoint,
                c.reflectV
        );
        Colour colour = colourAt(reflectedRay, remainingIterations - 1);
        return colour.scalarMultiply(c.object.material.reflectiveness);
    }

    public boolean isInShadow(Point p)
    {
        Vector lightToP = new Vector(p.minus(light.position));
        double distanceToPoint = lightToP.magnitude();
        Vector lightToPNormalised = lightToP.scalarDivide(distanceToPoint);
        Ray r = new Ray(light.position, lightToPNormalised);
        Intersection hit = intersections(r).hit();
        if (hit == null) return false;
        return !(hit.time > distanceToPoint);
    }
}
