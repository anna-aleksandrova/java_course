package homeworks.hw07;

import java.io.Serializable;

public class Toy implements Serializable {
    final private static long serialVersionUID = 1L;
    public String name;
    public double price;
    public int LowerBound;

    public Toy(String name, double price, int lb) {
        this.name = name;
        this.price = price;
        this.LowerBound = lb;
    }

    @Override
    public String toString() {
        return "Toy: name: " + name + "\n       price: " + price + "\n       lower bound: " + LowerBound + "\n";   
    }
}
