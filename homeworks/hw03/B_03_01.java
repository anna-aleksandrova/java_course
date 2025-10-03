package java_course.homeworks.hw03;

public class B_03_01 {
    public static void main(String[] args) {
        Complex[] roots_of_unity = {
            new Complex(1, 0),
            new Complex(0, 1),
            new Complex(-1, 0),
            new Complex(0, -1)
        };
        Complex res = multiply(roots_of_unity);
        System.out.println(multiply(roots_of_unity));
    }

    public static Complex multiply(Complex[] array) {
        Complex res = new Complex(1, 0);
        for (Complex el: array) {
            res = res.mult(el);
        }
        return res;
    }
}

class Complex {
    public final double real;
    public final double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex() {
        this(0, 0);
    }

    public Complex(Complex c) {
        this(c.real, c.imag);
    }

    @Override
    public String toString() {
        if (imag == 0) return "" + real;
        if (real == 0) return imag + "i";
        if (imag < 0) return real + "-" + (-imag) + "i";
        return real + " + " + imag + "i";
    }
 
    public boolean equals(Complex other) {
        return real == other.real && imag == other.imag;
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imag + other.imag);
    }

    /**
     * a = this.real
     * b = this.imag
     * c = other.real
     * d = other.imag
     * 
     * a + bi
     * c + di
     * 
     * (a + bi) * (c + di) = (ac - bd) + (ad + bc) * i
     */
    public Complex mult(Complex other) {
        return new Complex(this.real * other.real - this.imag * other.imag, this.real * other.imag + this.imag * other.real); 
    }

    public Complex subtract(Complex other) {
        return this.add(other.mult(new Complex(-1, 0)));
    }

    /**
     * (a + bi) ^(-1) = (a - bi) / (a^2 + b^2)
     */

    public Complex inverse() {
        double norm = this.real * this.real + this.imag * this.imag;
        return new Complex(this.real / norm, - this.imag / norm);
    }

    public Complex divide(Complex other) {
        return this.mult(other.inverse());
    }
}