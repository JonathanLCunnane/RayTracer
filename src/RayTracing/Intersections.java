package RayTracing;

import java.util.Arrays;

public class Intersections {
    public Intersection[] intersections;
    public int size;
    public Intersections(Intersection[] xs)
    {
        // If xs is not empty, then sort it.
        if (xs.length != 0)
        {
            Intersection[] xsCopy = xs.clone();
            Arrays.sort(xsCopy, (iOne, iTwo) -> {
                double diff = iOne.time - iTwo.time;
                if (diff > 0) return 1;
                else if (diff <= 0.00001 && diff >= -0.00001) return 0;
                return -1;
            });
            intersections = xsCopy;
        }
        else intersections = xs;
        size = xs.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersections xs = (Intersections) o;
        if (xs.size != size) return false;
        return Arrays.equals(xs.intersections, intersections);
    }

    public Intersection hit()
    {
        for (Intersection i: intersections)
        {
            if (i.time >= 0) return i;
        }
        return null;
    }
}
