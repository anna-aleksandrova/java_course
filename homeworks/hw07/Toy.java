package homeworks.hw07;

import java.io.Serializable;

public class Toy implements Serializable {
    final private static long serialVersionUID = 1L;
    public String name;
    public double price;
    public double LowerBound;

    public Toy(String name, double price, double lb) {
        this.name = name;
        this.price = price;
        this.LowerBound = lb;
    }
}
