import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A06_04 {

	public static void main(String[] args) {
        String input = "lsdkfj.253, -0.45, 123., +876.9, -.98, 5891. і 1.23";
        System.out.println(replace(input));
    }

    public static String replace(String s) {
        String regex = "([+-]?)(?:\\d+\\.\\d*|\\.\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String foundNumber = matcher.group();
            String replacement = foundNumber;

            if (foundNumber.matches("^[+-]?\\.\\d+$")) {
                if (foundNumber.startsWith("-")) {
                    replacement = foundNumber.replaceFirst("-", "-0");
                } else if (foundNumber.startsWith("+")) {
                    replacement = foundNumber.replaceFirst("\\+", "+0");
                } else {
                    replacement = "0" + foundNumber;
                }
            }
            else if (foundNumber.matches("^[+-]?\\d+\\.$")) {
                replacement = foundNumber + "0";
            }
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        
        matcher.appendTail(result);
        
        return result.toString();
    }
}