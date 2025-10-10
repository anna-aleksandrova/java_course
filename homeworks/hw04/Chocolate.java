package java_course.homeworks.hw04;

public class Chocolate extends Sweet {
    private String type;
    private String country;

    public Chocolate(double weight, double volume) {
        super(weight, volume);
    }

    public String getType() {return type;}
    public String getCountry() {return country;}
    public void setType(String type) {this.type = type;}
    public void setCountry(String country) {this.country = country;}

    @Override
    public double SugarAmount() {
        return getWeight() * 0.2 + getVolume() * 0.1;
    }

    @Override
    public String toString() {
        return "\nChocolate: weight: " + getWeight() + 
                "\n           volume: " + getVolume();
    }
}