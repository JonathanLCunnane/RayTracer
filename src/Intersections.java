import java.util.Arrays;

public class Intersections {
    public Intersection[] intersections;
    public int size;
    public Intersections(Intersection[] xs)
    {
        Intersection[] xsCopy = xs.clone();
        Arrays.sort(xsCopy, (iOne, iTwo) -> {
            double diff = iOne.time - iTwo.time;
            if (diff > 0) return 1;
            else if (diff <= 0.00001 && diff >= -0.00001) return 0;
            return -1;
        });
        intersections = xsCopy;
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
}
