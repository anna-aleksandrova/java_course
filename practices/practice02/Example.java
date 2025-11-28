public class Example {
    public static void main(String[] args) {
        int y = add(10, 20);
        
        BitsOps();
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static void BitsOps() {
        int x = 5;
        // System.out.println(Integer.toBinaryString(x));
        // System.out.println(x);

        // 1010 -> 10
        // 1100 -> 12
        System.out.println(10 & 12);  // 1000 -> 8
        System.out.println(10 | 12);  // 1110 -> 14
        System.out.println(10 ^ 12);  // 0110 -> 6
        // System.out.println(~10);

        System.out.println(10 << 1);  // 20
        System.out.println(10 >> 1);  // 5
    }
}