package practices.practice08;

import java.util.PriorityQueue;

public class B_08_05 {
    public static void main(String[] args) {
        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue();

        pq.add(new Pair<>("a", 1));
        pq.add(new Pair<>("b", 1));
        pq.add(new Pair<>("bcd", 2));
        pq.add(new Pair<>("askdjh", 3));

        while (pq.size() != 0) {
            System.out.println(pq.poll());
        } 
    }
}

class Pair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
    T1 first;
    T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair<T1, T2> other) {
        int firstComp = this.first.compareTo(other.first);
        if (firstComp != 0) return firstComp;

        return this.second.compareTo(other.second);
    }

    @Override
    public String toString() {
        return "Pair(" + first + ", " + second + ")";
    }
}
