package homeworks.hw05;

public class B_05_02b {
    public static void main(String[] args) {
        System.out.println(check("0"));
        System.out.println(check("1alskdfj2"));
        System.out.println(check("2aaa"));
        System.out.println(check("1"));
        System.out.println(check("lkj7fsa"));
    }

    public static boolean check(String st) {
        StringBuilder s = new StringBuilder(st);
        int cnt = 0;
        int digit = -1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                cnt++;
                digit = Character.getNumericValue(s.charAt(i));
            }
        }
        if (cnt != 1 || s.length() != digit) return false;
        return true;
    }
}
