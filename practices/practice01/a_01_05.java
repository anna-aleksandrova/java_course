public class a_01_05 {
    public static void main(String[] args) {
        String M = args[0];
        String N = args[1];
        System.out.println("Digit amount: ");
        System.out.println("M: " + M.length + " N: " + N.length);
        System.out.println("M: " + (int) Math.log10(M) + " N: " + (int) Math.log10(N));
    }
}