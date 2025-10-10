package java_course.homeworks.hw04;

public class Main {

    public static void main(String[] args) {
        Sweet[] sweets = {
            new Chocolate(200, 105),
            new Chocolate(250, 105),
            new Chocolate(200, 300),
            new Caramel(10, 3),
            new Caramel(20, 10)
        };
        Present present = new Present(sweets);
        System.out.println(present);
        System.out.println("\nTotal weight (grams): " + present.getTotalWeight());
        System.out.println("\nSorted: ");
        present.sort();
        System.out.println(present);
        System.out.println("\nSugar amount: ");
        for (Sweet s: present.sweets) {
            System.out.print(s.SugarAmount() + " ");
        }
        System.out.println();


        Present interval = present.FromIntervalOfSugar(50, 80);
        System.out.println("\nPresent consisting of sweets which contain from 50 to 80 grams of sugar: ");
        System.out.println(interval);
    }
}