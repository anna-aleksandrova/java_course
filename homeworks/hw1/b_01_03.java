public class b_01_03 {
    public static void main(String[] args) {
        try {
            int M = Integer.parseInt(args[0]);
            int N = Integer.parseInt(args[1]);
            System.out.println(M * N);
        }
        catch (Exception e) {
            System.out.println("The arguments must be integers.");
        }

    }
}