package homeworks.hw06;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class B_06_02 {
    public static void main(String[] args) {
        String regex = "(\\+380|380|0|\\(0)\\s*(\\(|-|)\\d{2}\\s*(\\)||-|\\)-)\\s*\\d{3}\\s*(||-)\\s*\\d{2}\\s*(-|)\\s*\\d{2}";
        
        String s = "+380 (67) 123 45 67\n\n"
                    + "050-987-65-43\n\n"
                    + "0931112233\n\n"
                    + "(044) 555-66-77\n\n"
                    + "+380975554433\n\n"
                    + "063 888 99 00\n\n"
                    + "+380-50-111-22-33\n\n"
                    + "099 765 43 21\n\n"
                    + "(067) 456-78-90\n\n"
                    + "0441234567\n\n"
                    + "+380 (95) 111 22 33\n\n"
                    + "097-444-5555 \n\n"
                    + "+380 66 123 4567\n\n"
                    + "(093)8765432 \n\n"
                    + "+380449876543\n\n"
                    + "032 244 55 66 \n\n"
                    + "(057) 700-11-22 ";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        String res = m.replaceAll("number");
        System.out.println(res);
    }
}
