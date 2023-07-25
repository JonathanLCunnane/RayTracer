public class Intersection {
    public Object object;
    public double time;
    public Intersection(Object obj, double t)
    {
        object = obj;
        time = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection i = (Intersection) o;
        if (i.object != object) return false;
        return i.time == time;
    }
}
