public class a_01_02 {
    public static void main(String[] args) {
        double sum = 0;
        for (int i = 0; i < args.length; i++) {
            sum += Double.parseDouble(args[i]);
        }
        System.out.println(sum);
    }
}

// byte          1
// short         2
// int           4
// long          8
// double        8
// float         4
// char          2
// boolean       1