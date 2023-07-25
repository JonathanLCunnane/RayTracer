import java.util.Arrays;

public class Intersections {
    public Intersection[] intersections;
    public int size;
    public Intersections(Intersection[] xs)
    {
        intersections = xs;
        size = xs.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersections xs = (Intersections) o;
        if (xs.size != size) return false;
        return Arrays.equals(Arrays.stream(xs.intersections.clone()).sorted().toArray(), Arrays.stream(intersections.clone()).sorted().toArray());
    }
}
