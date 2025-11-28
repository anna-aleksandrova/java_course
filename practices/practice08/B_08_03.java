package practices.practice08;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class B_08_03 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("practices/practice08/input03.txt");
            Scanner in = new Scanner(fr);

            HashMap<String, Integer> words = new HashMap<>();

            while (in.hasNext()) {
                String curr = in.next();
                if (!words.containsKey(curr)) words.put(curr, 1);
                else words.put(curr, words.get(curr)+1);
            }
            in.close();

            System.out.println(words);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
