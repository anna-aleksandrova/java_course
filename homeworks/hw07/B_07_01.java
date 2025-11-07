package homeworks.hw07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class B_07_01 {
    public static void main(String[] args) {
        String F = "homeworks/hw07/F.dat";
        String G = "homeworks/hw07/G.dat";
        List<Double> array = Arrays.asList(1.23, -0.123098, 989.0, 12398.23, .23, 0.0, 1.0, 1.0);

        write(F, array);
        buildG(F, G, 0);
        List<Double> lst = read(G);
        System.out.println(lst);
    }

    public static void write(String filename, List<Double> data) {
        try (
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream fout = new DataOutputStream(bos);
        ) {
            for (double number : data) fout.writeDouble(number);
        } catch (IOException e) {
            System.err.println("Error while writing into file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Double> read(String filename) {
        List<Double> numbers = new ArrayList<>();
        try (
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream fin = new DataInputStream(bis);
        ) {
            while (true) {
                try {
                    double n = fin.readDouble();
                    numbers.add(n);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found " + filename);
        } catch (IOException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
        }
        return numbers;
    }

    public static void buildG(String F, String G, double a) {
        List<Double> fromF = read(F);
        List<Double> toG = new ArrayList<Double>();
        for (double n: fromF) {
            if (n > a) toG.add(n);
        }
        write(G, toG);
    }
}
