package practices.practice08;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class B_08_01 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("practices/practice08/input01.txt");
            Scanner in = new Scanner(fr);

            List<String> lines = new ArrayList<>();

            while (in.hasNextLine()) {
                lines.add(in.nextLine());
            }

            Collections.reverse(lines);

            FileWriter fw = new FileWriter("practices/practice08/output01.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (String s: lines) {
                pw.write(s);
                pw.write("\n");
            }
            
            pw.close();
            in.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
