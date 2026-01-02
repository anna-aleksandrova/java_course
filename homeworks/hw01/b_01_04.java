import java.util.Scanner;

public class b_01_04 {
    public static void main(String[] args) {
        int[] a = new int[3];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.print(">>> ");
            a[i] = in.nextInt();
        }
        double res = Math.pow(a[0] * a[1] * a[2], 1.0/3);
        System.out.printf("Geometric mean: %.4f", res);
        System.out.println();
    }
}