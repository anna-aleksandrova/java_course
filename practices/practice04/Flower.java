package java_course.practices.practice04;

public abstract class Flower implements Comparable<Flower> {

    private double length;
    private double freshness;

    public Flower(double length, double freshness) {
        this.length = length;
        this.freshness = freshness;
    }

    public double getlength() {return length;}
    public void setlength(double length) {this.length = length;}
    public double getfreshness() {return freshness;}
    public void setfreshness(double freshness) {this.freshness = freshness;}

    public abstract double getPrice();

    @Override
    public int compareTo(Flower other) {
        if (freshness > other.freshness) return 1;
        else if (freshness < other.freshness) return -1;
        else return 0;
    }
}