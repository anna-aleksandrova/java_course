package homeworks.hw05;

import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileWriter;

public class B_05_03 {
    public static void main(String[] args) {
        Path p = Path.of("homeworks/hw05/input.txt");
        double len = 0;
        String interval = "";
        double r_area = 0;
        String rectangle = "";
        double c_area = 0;
        String circle = "";
        double temp;
        try (BufferedReader in = Files.newBufferedReader(p)) {
            String line;
            String[] words = new String[5];

            while (true) {
                line = in.readLine();
                if (line == null) break;
                words = line.split("\\s+");
                if (words[0].equals("1")) {
                    temp = Math.abs(Double.parseDouble(words[1]) - Double.parseDouble(words[2]));
                    if (temp > len) {
                        len = temp;
                        interval = line;
                    }
                }
                if (words[0].equals("2")) {
                    temp = Math.abs(Double.parseDouble(words[1]) - Double.parseDouble(words[3])) * Math.abs(Double.parseDouble(words[2]) - Double.parseDouble(words[4]));
                    if (temp > r_area) {
                        r_area = temp;
                        rectangle = line;
                    }
                }
                if (words[0].equals("3")) {
                    temp = Math.pow(Double.parseDouble(words[3]), 2) * Math.PI;
                    if (temp > c_area) {
                        c_area = temp;
                        circle = line;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to open file.");
        }
        try (FileWriter out = new FileWriter("homeworks/hw05/output.txt")) {
            out.write(interval + "\n");
            out.write(rectangle + "\n");
            out.write(circle + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write into the file.");
        }
    }
}
