package RayTracing;

import Display.Colour;
import RayTracing.Objects.ParentObject;
import Tuples.Point;
import Tuples.Vector;

import java.util.Objects;

public class World {
    public ParentObject[] objects;
    public PointLight[] lights;

    public World()
    {
        objects = new ParentObject[] {};
        lights = new PointLight[] {};
    }

    public World(ParentObject[] objs, PointLight lightSource)
    {
        objects = objs;
        lights = new PointLight[] {lightSource};
    }

    public World(ParentObject[] objs, PointLight[] lightSources)
    {
        objects = objs;
        lights = lightSources;
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
        Colour sum = new Colour(0, 0, 0);
        for (PointLight light: lights)
        {
            boolean inShadow = isInShadow(c.overPoint, light);
            Colour surfaceColour = c.object.material.lightningAtPoint(
                    light,
                    c.point,
                    c.eyeV,
                    c.normalV,
                    inShadow,
                    c.object
            );
            Colour reflectedColour = reflectedColour(c, remainingIterations);
            Colour refractedColour = refractedColour(c, remainingIterations);
            if (c.object.material.reflectiveness > 0 && c.object.material.transparency > 0)
            // An approximation of the Fresnel effect
            {
                double reflectance = c.schlick();
                return surfaceColour.plus(
                        reflectedColour.scalarMultiply(reflectance)
                ).plus(
                        refractedColour.scalarMultiply(1 - reflectance)
                );
            }
            sum = sum.plus(surfaceColour.plus(reflectedColour).plus(refractedColour));
        }
        return sum;
    }

    public Colour colourAt(Ray r, int remainingIterations)
    {
        Intersections xs = intersections(r);
        Intersection i = xs.hit();
        if (i == null) return new Colour(0, 0, 0); // Black is returned if there is no object intersection
        Computations c = i.prepareRefractionComputations(r, xs);
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

    public Colour refractedColour(Computations c, int remainingIterations)
    {
        if (remainingIterations <= 0) return new Colour(0, 0, 0);
        if (c.object.material.transparency == 0) return new Colour(0, 0, 0);
        // From Snell's Law, nOne * sinI = nTwo * sinT
        double nRatio = c.nOne / c.nTwo;
        double cosI = c.eyeV.dot(c.normalV);
        double sinTSquared = Math.pow(nRatio, 2) * (1 - Math.pow(cosI, 2));
        if (sinTSquared > 1) return new Colour(0, 0, 0); // Total internal reflection.
        double cosT = Math.sqrt(1 - sinTSquared);
        Vector direction = new Vector(c.normalV.scalarMultiply((nRatio * cosI) - cosT).minus(c.eyeV.scalarMultiply(nRatio)));
        Ray refractedRay = new Ray(c.underPoint, direction);
        Colour colour = colourAt(refractedRay, remainingIterations - 1);
        return colour.scalarMultiply(c.object.material.transparency);
    }

    public boolean isInShadow(Point p) // Uses the first light in lights.
    {
        Vector lightToP = new Vector(p.minus(lights[0].position));
        double distanceToPoint = lightToP.magnitude();
        Vector lightToPNormalised = lightToP.scalarDivide(distanceToPoint);
        Ray r = new Ray(lights[0].position, lightToPNormalised);
        Intersection hit = intersections(r).hit();
        if (hit == null) return false;
        return (!(hit.time > distanceToPoint) && hit.object.material.castsShadow);
    }

    public boolean isInShadow(Point p, PointLight light)
    {
        Vector lightToP = new Vector(p.minus(light.position));
        double distanceToPoint = lightToP.magnitude();
        Vector lightToPNormalised = lightToP.scalarDivide(distanceToPoint);
        Ray r = new Ray(light.position, lightToPNormalised);
        Intersection hit = intersections(r).hit();
        if (hit == null) return false;
        return (!(hit.time > distanceToPoint) && hit.object.material.castsShadow);
    }

    public void addObject(ParentObject o)
    {
        ParentObject[] newObjects = new ParentObject[objects.length + 1];
        System.arraycopy(objects, 0, newObjects, 0, objects.length);
        newObjects[objects.length] = o;
        objects = newObjects;
    }
}
