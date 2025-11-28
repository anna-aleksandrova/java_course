package practices.practice07;

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

// possible to implement through ByteBuffer

public class A_07_01 {
    public static void main(String[] args) {
        String F = "practices/practice07/F.dat";
        String G = "practices/practice07/G.dat";
        int[] array = {1, 2, -10, 100, 100, -45, 4};

        write(F, array);
        List<Integer> arr = read(F);
        List<Integer> even = new ArrayList<>();
        for (int n: arr) {
            if (n % 2 == 0) even.add(n);
        }
        write(G, array);
    }

    public static List<Integer> read(String file) {
        List<Integer> numbers = new ArrayList<>();
        try (
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream fin = new DataInputStream(bis)
        ) {
            while (true) {
                try {
                    int number = fin.readInt();
                    numbers.add(number);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found " + file);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return numbers;
    }

    public static void write(String filename, int[] data) {
        try (
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream fout = new DataOutputStream(bos)
        ) {
            for (int number : data) {
                fout.writeInt(number);
            }
        } catch (IOException e) {
            System.err.println("Error while writing into file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
