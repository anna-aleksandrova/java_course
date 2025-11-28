import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A_06_04 {
    public static void main(String[] args) {
        String DATE1 = "\\b\\d{1,2}\\.\\d{1,2}\\.\\d{4}\\b";
		String DATE2 = "\\b\\d{1,2}-\\d{1,2}-\\d{4}\\b";
		String DATE3 = "\\b\\d{4}/\\d{1,2}/\\d{1,2}\\b";
		String DATE = DATE1 + "|" + DATE2 + "|" + DATE3;
		
        String s = "Привіт\r\n"
				+ "Mr. Smith bought ----che-apa@site.com---- for 1.5 million dollars, i.e. he paid a lot for it! 9.12.2020 MY@URK.net\r\n"
				+ "Did he mind? MY.MAIL@MAIL.COM Adam Jones Jr. thinks he didn't. In any case, this isn't true... 03-03-2000, 2019/8/10\r\n"
				+ "Well, with a probability of 0.9 it isn't. \r\n"
				+ "Amount: 231.30. Hello! mail@knu.ua";
		
		Pattern p = Pattern.compile(DATE);
		Matcher m = p.matcher(s);
		
		System.out.println(m.replaceAll(x -> replaceD(x)));
    }

    public static String replaceD(Matcher m) {
        String date = s.group();
        String d, month, year;
        if (date.contains(".")) {
            d = m.group("d1");
            month = m.group("m1");
            year = m.group("y1");
        }
        if (date.contains("-")) {
            d = m.group("d2");
            month = m.group("m2");
            year = m.group("y2");
        }
        if (date.contains("/")) {
            d = m.group("d3");
            month = m.group("m3");
            year = m.group("y3");
        }

        if (day.length() < 2) day = "0" + day;
        if (month.length() < 2) month = "0" + month;
        return day + "." + month + "." + year;
    }
}
