package homeworks.hw05;

public class B_05_02c {
    public static void main(String[] args) {
        System.out.println(check("0"));
        System.out.println(check("1kj2"));
        System.out.println(check("4aaa"));
        System.out.println(check("1"));
        System.out.println(check("l124fsa"));
    }

    public static boolean check(String st) {
        StringBuilder s = new StringBuilder(st);
        int sum = 0;
        int digit = -1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                digit = Character.getNumericValue(s.charAt(i));
                sum += digit;
            }
        }
        return sum == s.length();
    }
}

