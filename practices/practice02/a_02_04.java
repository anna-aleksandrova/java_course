public class a_02_04 {
    public static void main(String[] args) {
        // System.out.println(Math.ulp(1.0));
        System.out.println(f(1.71, 2));
    }

    public static double f(double x, double eps) {
        if (Math.abs(x) < Math.ulp(1.0)) return 0;
        else {
            double fn = x;
            int i = 1;
            double sum = 0;
            while (i < 100) {
                sum += fn;
                i++;
                fn = (-1) * fn * x / i;
            }
            return sum;
        }
    }
}