package java_course.practices.practice04;

public class Peony extends Flower {

    public Peony(double length, double freshness) {
        super(length, freshness);
    }

    @Override
    public String toString() {
        return "Кульбаба довжини" + getlength() + "та свіжості " + getfreshness();
    }

    @Override
    public double getPrice() {
        return 10.0 + getlength() * 0.7 + 0.5 * getfreshness();
    }
} 
