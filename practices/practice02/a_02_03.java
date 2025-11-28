// 0 1 1 2 3 5 8 13 21 ...

public class a_02_03 {
    public static void main(String[] args) {
        System.out.println(Fibonacci(3));
        System.out.println(Fibonacci(4));
        System.out.println(Fibonacci(5));
    }

    // recursive
    public static int fibonacci(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else return fibonacci(n-2) + fibonacci(n-1);
    }

    // recurrent
    public static int Fibonacci(int n) {
        int[] fib = new int[n+1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) fib[i] = fib[i-2] + fib[i-1];
        return fib[n];
    }
}