package practices.practice08;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class B_08_02 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("practices/practice08/input02.txt");
            Scanner in = new Scanner(fr);

            HashSet<String> words = new HashSet<>();

            while (in.hasNext()) {
                String curr = in.next().toLowerCase();
                if (!words.contains(curr)) words.add(curr);
            }

            in.close();

            System.out.println(words);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
