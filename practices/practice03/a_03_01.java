package java_course.practices.practice03;
import java.util.Arrays;

public class a_03_01 {
    public static void main(String[] args) {
        int size = 4;
        Fraction[] array = new Fraction[size];
        for (int i = 0; i < size; i++) {
            array[i] = Fraction.random();
        }
        System.out.println(Arrays.toString(array));
        System.out.println(Fraction.sum(array));
    }
}

class Fraction {
    protected int m;
    protected int n;

    public Fraction(int m, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Denominator must be positive");
        }
        this.m = m;
        this.n = n;
        reduce(this);
    }

    public Fraction(Fraction f) {
        this(f.m, f.n);
    }

    @Override
    public String toString() {
        return m + "/" + n;
    }

    public boolean equals(Fraction other) {
        return m == other.m && n == other.n;
    }

    public static int gcd(int m, int n) {
        while (n > 0) {
            int r = m % n;
            m = n;
            n = r;
        }
        return m;
    }

    public static Fraction sum(Fraction[] fracs) {
        Fraction res = new Fraction(0, 1);
        for (Fraction item: fracs) {
            res = res.add(item);
        }
        return res;
    }

    public void reduce(Fraction f) {
        int _gcd = Fraction.gcd(Math.abs(f.m), f.n);
        f.m = f.m / _gcd;
        f.n = f.n / _gcd;
    }

    public Fraction add(Fraction other) {
        int m = this.m * other.n + other.m * this.n;
        int n = this.n * other.n;
        return new Fraction(m, n);
    }

    public static Fraction random() {
        int m = (int) (Math.random() * 200 - 100);
        int n = (int) (Math.random() * 100 + 1);
        return new Fraction(m, n);
    }
}
