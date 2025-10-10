package java_course.homeworks.hw04;

public class Caramel extends Sweet {
    
    public Caramel(double weight, double volume) {
        super(weight, volume);
    }

    @Override
    public double SugarAmount() {
        return 0.5 * getWeight() + 0.7 * getVolume();
    }

    @Override
    public String toString() {
        return "\nCaramel: weight: " + getWeight() + 
                "\n         volume: " + getVolume();
    }
}