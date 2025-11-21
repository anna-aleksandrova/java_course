package homeworks.hw09;

public class Log {

    public static long start = System.currentTimeMillis();

    public static void print(String msg) {
        System.out.printf(
                "[%6.2f] %s: %s\n",
                (System.currentTimeMillis() - start) / 1000.0,
                Thread.currentThread().getName(),
                msg
        );
    }
}
