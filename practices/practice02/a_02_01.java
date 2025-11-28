import java.util.Scanner;

public class a_02_01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter k: ");
        int k = in.nextInt();
        System.out.print("Enter the length of the array: ");
        int n = in.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int elem = in.nextInt();
            if (elem % k == 0) cnt++;
        }
        System.out.println("The amount of multiples: " + cnt);

    }
}