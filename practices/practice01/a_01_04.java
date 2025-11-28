import java.util.Scanner;

public class a_01_04 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String number = in.nextLine();
        String[] parts = number.split("\\.");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        in.close();
    }
}