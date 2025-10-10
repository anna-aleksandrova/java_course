package java_course.homeworks.hw04;

import java.util.Arrays;
import java.util.ArrayList;

public class Present {

    public Sweet[] sweets;

    public Present(Sweet[] sweets) {
        this.sweets = sweets;
    }

    @Override
    public String toString() {
        return Arrays.toString(sweets);
    }

    public void sort() {
        Arrays.sort(sweets);
    }

    public double getTotalWeight() {
        double res = 0.0;
        for (Sweet s: sweets) {
            res += s.getWeight();
        }
        return res;
    }

    public Present FromIntervalOfSugar(double a, double b) {
        ArrayList<Sweet> lst = new ArrayList<>();
        for (Sweet s: sweets) {
            if (a < s.SugarAmount() && s.SugarAmount() < b) lst.add(s);
        }
        Sweet[] array = lst.toArray(new Sweet[0]);
        return new Present(array);
    }
}