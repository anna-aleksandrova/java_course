import java.util.Scanner;

public class B02_02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter an integer between 0 and 255: ");
        int n = in.nextInt();
        while (n < 0 || n > 255) {
            System.out.println("Incorrect value entered.");
            System.out.print("Enter an integer between 0 and 255: ");
            n = in.nextInt();
        }
        int lsb = n >> 7;
        n = (n << 1) & 255;
        n = n | lsb;
        System.out.println(n);
    }
}