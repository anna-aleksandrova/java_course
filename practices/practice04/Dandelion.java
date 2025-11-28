package java_course.practices.practice04;

public class Dandelion extends Flower {

    public Dandelion(double length, double freshness) {
        super(length, freshness);
    }

    @Override
    public String toString() {
        return "Кульбаба довжини" + getlength() + "та свіжості " + getfreshness();
    }

    @Override
    public double getPrice() {
        return 5.0 + getlength() * 0.3 + getfreshness();
    }
} 
