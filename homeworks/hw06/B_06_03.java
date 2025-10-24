package homeworks.hw06;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class B_06_03 {
    public static void main(String[] args) {
        String s1 = "+2 -     57*33     + 25/  -  4 ";   // true
        String s2 = "+2 -     57*/33     + 25/  -  4 ";  // false
        String s3 = "+2 -     57*33     +  -  4 ";       // true

        System.out.println(correct(s1));
        System.out.println(correct(s2));
        System.out.println(correct(s3));

    }

    public static boolean correct(String exp) {
        String correct = "\\s*[+-]?\\s*\\d+(\\s*[+\\-*\\/]\\s*[+-]?\\s*\\d+)*\\s*";

        Pattern p = Pattern.compile(correct);
        Matcher m = p.matcher(exp);
        return m.matches();
    }
}
