package homeworks.hw08;

import java.util.PriorityQueue;

public class B_08_04 {
    public static void main(String[] args) {
        
        PriorityQueue<Point> pq = new PriorityQueue<>();

        pq.add(new Point(3, 4));
        pq.add(new Point(1, 1));
        pq.add(new Point(0, 5));
        pq.add(new Point(10, 10));
        pq.add(new Point(0, 0));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}

class Point implements Comparable<Point> {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double d() {
        return Math.sqrt(x * x + y * y);
    }
    @Override
    public int compareTo(Point other) {
        return Double.compare(this.d(), other.d());
    }

    @Override
    public String toString() {
        return String.format("d((0, 0), (%d, %d)): %.2f", x, y, d());
    }
}
