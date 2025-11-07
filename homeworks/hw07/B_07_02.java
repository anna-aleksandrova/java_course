package homeworks.hw07;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class B_07_02 {
    public static void main(String[] args) {
        String F = "homeworks/hw07/F2.dat";
        String G = "homeworks/hw07/G2.dat";

        List<Toy> toys = new ArrayList<Toy>();
        Toy t1 = new Toy("Ball", 200, 5);
        Toy t2 = new Toy("Car", 50, 3);
        Toy t3 = new Toy("Puzzle", 100, 7);
        toys.add(t1);
        toys.add(t2);
        toys.add(t3);

        write(F, toys);
    }

    public static void write(String file, List<Toy> lst) {
        try (
            var f = new ObjectOutputStream(new FileOutputStream(file));
        ) {
            for (var toy: lst) {
                f.writeObject(toy);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Toy> read(String file) {
        List<Toy> res = new ArrayList<>();

        try (
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
        )
    }
}
