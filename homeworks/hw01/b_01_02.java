public class b_01_02 {
    public static void main(String[] args) {
        int N = args.length;
        for (int i = 0; i < N; i++) {
            System.out.println(args[N - i - 1]);
        }
    }
}