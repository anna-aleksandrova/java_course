import java.util.Scanner;

public class a_02_02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = in.nextInt();
        System.out.print("Enter k: ");
        int k = in.nextInt();
        System.out.println(n ^ 1<<k);
    }
}