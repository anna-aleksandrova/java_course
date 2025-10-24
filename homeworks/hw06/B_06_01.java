package homeworks.hw06;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B_06_01 {
    public static void main(String[] args) {
        String regex = "\\d{2}\\.\\d{2}\\.\\d{4}|_{2}\\._{2}\\._{4}";
        LocalDate today = LocalDate.now();

        String s = "The project was officially launched on 01.10.2025. Our next major milestone is scheduled for 15.11.2025, "
                    + "while the preliminary report is due on __.__.____. The final system audit will take place on 20.12.2025, " 
                    + "and the follow-up review is now set for __.__.____. All work must be completed by the final deadline of "
                    + "31.01.2026, with the project closure meeting tentatively planned for __.__.____.";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        String res = m.replaceAll(today.toString());
        System.out.println(res);
    }
}

/*
 * Output:
 * The project was officially launched on 2025-10-24. Our next major milestone is scheduled for 2025-10-24, 
 * while the preliminary report is due on 2025-10-24. The final system audit will take place on 2025-10-24, 
 * and the follow-up review is now set for 2025-10-24. All work must be completed by the final deadline of 
 * 2025-10-24, with the project closure meeting tentatively planned for 2025-10-24.
 */
