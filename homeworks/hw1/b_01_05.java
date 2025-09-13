import java.util.Scanner;

public class b_01_05 {
    public static void main(String[] args) {
        int N, M;
        try {
            N = Integer.parseInt(args[0]);
            M = Integer.parseInt(args[1]);
        }
        catch (Exception e) {
            Scanner in = new Scanner(System.in);
            System.out.print(">>> ");
            N = in.nextInt();
            System.out.print(">>> ");
            M = in.nextInt();
        }
        for (int i = 0; i < N; i++) {
            int random = (int) (Math.random() * M);
            System.out.println(random);
        }
    }
}