package practices.practice07;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Luggage implements Serializable {

    final private static long serialVersionUID = 1L;
    public String name;
    public int count;
    public double weight;

    public Luggage(String name, int count, double weight) {
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    // @Override
    // public 

    public static void write(String out, ArrayList<Luggage> lst) {
        try {
            var f = new ObjectOutputStream(new FileOutputStream(out));
            for (var luggage : lst) {
                f.writeObject(luggage);
            }
            f.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Luggage> read(String inp) {
        List<Luggage> lst = new ArrayList<>();
        try {
            
        }
    }
}
