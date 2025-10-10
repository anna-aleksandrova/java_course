package java_course.homeworks.hw04;

public abstract class Sweet implements Comparable<Sweet> {
    
    private double weight;
    private double volume;

    public Sweet(double weight, double volume) {
        this.weight = weight;
        this.volume = volume;
    }

    public double getWeight() {return weight;}
    public double getVolume() {return volume;}
    public void setWeight(double weight) {this.weight = weight;}
    public void setVolume(double volume) {this.volume = volume;}

    public abstract double SugarAmount();

    @Override
    public int compareTo(Sweet other) {
        if (volume > other.volume) return 1;
        else if (volume < other.volume) return -1;
        else return 0;
    }
}