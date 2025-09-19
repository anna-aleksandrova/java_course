import java.util.Scanner;

public class B02_01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Length of the array\n>>> ");
        int size = in.nextInt();
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextDouble();
        }
        System.out.println(min(array));
    }

    public static double min(double[] array) {
        double res = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < res) res = array[i];
        }
        return res;
    }
}
