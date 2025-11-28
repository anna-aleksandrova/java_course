// \b[a-z0-9_\-\.]+?@[a-z0-9_\-\]+\b

package java_course.practices.practice06;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A_06_01 {
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("input.txt");
        String s = Files.readString(filePath);

        String rgx = "\\b[a-z0-9_\\-\\.]+?@[a-z0-9_\\-\\]+\\b";
        Pattern p = Pattern.compile(rgx, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
