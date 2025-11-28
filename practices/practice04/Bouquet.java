package java_course.practices.practice04;

public class Bouquet {
    public Flower[] flowers;

    public Bouquet(Flower[] flowers) {
        this.flowers = flowers;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.flowers);
    }

    public double getPrice() {
        double res = 0;
        for (Flower flower: flowers) {
            res += flower.getPrice();
        }
        return res;
    }
}
