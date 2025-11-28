package java_course.practices.practice06;
// [A-ZА-ЯІЇҐЄ].*?[\.\!\?](?<![A-ZА-ЯІЇҐЄ\.]?\w\.)
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A_06_03 {
    public static void main(String[] args) {
        String s = "Розбити текст у текстовому файлі на речення.\r\n"
				+ "Mr. Smith bought cheapsite.com for 1.5 million dollars, i.e. he paid a lot for it! \r\n"
				+ "Did he mind? Adam Jones Jr. thinks he didn't. In any case, this isn't true... \r\n"
				+ "Well, with a probability of 0.9 it isn't. Amount: 231.30. Hello!";
		
		String rgx = "[A-ZА-ЯЇІҐЄ].*?[\\.\\!\\?](?<![A-Z\\.]\\w\\.)(?=\\s|$)";
		
		Pattern p = Pattern.compile(rgx);
		Matcher m = p.matcher(s);
		while (m.find())
			System.out.println(m.group());
    } 
}
