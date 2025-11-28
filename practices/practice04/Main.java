package java_course.practices.practice04;

public class Main {
    public static void main(String[] args) {
        Flower[] flowers = {
            new Dandelion(5.0, 3.2),
            new Peony(7.0, 2.5),
            new Peony(5.2, 3.3),
            new Dandelion(6.7, 4.5),
            new Dandelion(5.0, 5.0)
        };
        Bouquet bouquet = new Bouquet(flowers);
        System.out.println(bouquet);
        System.out.println(bouquet.getPrice());
    }
}
