import java.util.Scanner;

public class B02_17 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter x: ");
        double x = in.nextDouble();
        while (!(x > -1 && x < 1)) {
            System.out.println("The series converges only on (-1, 1).");
            System.out.print("Enter x: ");
            x = in.nextDouble();
        }
        System.out.print("Enter epsilon: ");
        double eps = in.nextDouble();
        double current = 1;
        double sum = 0;
        while (Math.abs(current) > eps) {
            sum += current;
            current *= -x;
        }
        System.out.println(sum);
    }
} 