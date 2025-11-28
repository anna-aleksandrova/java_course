package java_course.practices.practice05;

public class A_05_01 {

    public static void main(String[] args) {
        // String s = "Hello, 234, world! 12 asflk234 123098";
        // System.out.println(d(s));

        // String s1 = "4+++++++3+";
        // System.out.println(del_plus(s1));

        String s2 = "phphe    lepha\t\t    ntp\n\n\n\n   h";
        System.out.println(spl(s2));
    }

    public static String d(String s) {
        char[] res = new char[s.length() * 2];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            res[j++] = s.charAt(i);
            if (Character.isDigit(s.charAt(i))) res[j++] = s.charAt(i);
        }
        return new String(res);
    }

    public static String del_plus(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        while (true) {
            i = sb.indexOf("+", i);
            if (i == -1 || i + 1 == sb.length()) break;
            if (Character.isDigit(sb.charAt(i+1))) {
                sb.deleteCharAt(i);
                if (i > 0) i--;
            }
            else i++;
        }
        return sb.toString();
    }

    public static String r(String s) {
        return s.replaceAll("ph", "r");
    }

    public static String spl(String s) {
        return s.replaceAll("\\s+", " ");
    }
}
